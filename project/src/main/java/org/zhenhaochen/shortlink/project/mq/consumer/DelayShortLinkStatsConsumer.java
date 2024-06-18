package org.zhenhaochen.shortlink.project.mq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zhenhaochen.shortlink.project.common.convention.exception.ServerException;
import org.zhenhaochen.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.zhenhaochen.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import org.zhenhaochen.shortlink.project.service.ShortLinkService;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import static org.zhenhaochen.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;

/**
 * Delay Record Short Link Statistics Component
 */
@Deprecated
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsConsumer implements InitializingBean {

    private final RedissonClient redissonClient;
    private final ShortLinkService shortLinkService;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    public void onMessage() {
        Executors.newSingleThreadExecutor(
                        runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setName("delay_short-link_stats_consumer");
                            thread.setDaemon(Boolean.TRUE);
                            return thread;
                        })
                .execute(() -> {
                    RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY);
                    RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
                    for (; ; ) {
                        try {
                            ShortLinkStatsRecordDTO statsRecord = delayedQueue.poll();
                            if (statsRecord != null) {
                                if (messageQueueIdempotentHandler.isMessageProcessed(statsRecord.getKeys())) {
                                    if (messageQueueIdempotentHandler.isAccomplished(statsRecord.getKeys())) {
                                        return;
                                    }
                                    throw new ServerException("the message is processed but not accomplished, message queue will retry");
                                }
                                try {
                                    shortLinkService.shortLinkStats(null, null, statsRecord);
                                } catch (Throwable ex) {
                                    messageQueueIdempotentHandler.delMessageProcessed(statsRecord.getKeys());
                                    log.error("fail to delayed record short link monitor statistic", ex);
                                    throw ex;
                                }
                                messageQueueIdempotentHandler.setAccomplished(statsRecord.getKeys());
                                continue;
                            }
                            LockSupport.parkUntil(500);
                        } catch (Throwable ignored) {
                        }
                    }
                });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        onMessage();
    }
}
