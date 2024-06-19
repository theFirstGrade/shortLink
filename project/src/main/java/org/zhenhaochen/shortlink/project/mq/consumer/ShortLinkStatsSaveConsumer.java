package org.zhenhaochen.shortlink.project.mq.consumer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import org.zhenhaochen.shortlink.project.common.convention.exception.ServerException;
import org.zhenhaochen.shortlink.project.dao.entity.*;
import org.zhenhaochen.shortlink.project.dao.mapper.*;
import org.zhenhaochen.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.zhenhaochen.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.zhenhaochen.shortlink.project.common.constant.RedisKeyConstant.LOCK_GID_UPDATE_KEY;

/**
 * Short Link Monitor Statistic Saving Consumer
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkGotoMapper shortLinkGotoMapper;
    private final RedissonClient redissonClient;
    private final LinkAccessStatsMapper linkAccessStatsMapper;
    private final LinkLocaleStatsMapper linkLocaleStatsMapper;
    private final LinkOsStatsMapper linkOsStatsMapper;
    private final LinkBrowserStatsMapper linkBrowserStatsMapper;
    private final LinkAccessLogsMapper linkAccessLogsMapper;
    private final LinkDeviceStatsMapper linkDeviceStatsMapper;
    private final LinkNetworkStatsMapper linkNetworkStatsMapper;
    private final LinkStatsTodayMapper linkTodayStatsMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    @Value("${short-link.stats.locale.IPInfo-key}")
    private String statsLocaleIPInfoKey;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        String stream = message.getStream();
        RecordId id = message.getId();
        // judge the current message is processed - NOTE: being processed does not mean being accomplished
        if (messageQueueIdempotentHandler.isMessageProcessed(id.toString())) {
            // judge the current message is accomplished.
            if (messageQueueIdempotentHandler.isAccomplished(id.toString())) {
                return;
            }
            throw new ServerException("the message is processed but not accomplished, message queue will retry");
        }
        try {
            Map<String, String> producerMap = message.getValue();
            String fullShortUrl = producerMap.get("fullShortUrl");
            if (StrUtil.isNotBlank(fullShortUrl)) {
                String gid = producerMap.get("gid");
                ShortLinkStatsRecordDTO statsRecord = JSON.parseObject(producerMap.get("statsRecord"), ShortLinkStatsRecordDTO.class);
                actualSaveShortLinkStats(fullShortUrl, gid, statsRecord);
            }
            stringRedisTemplate.opsForStream().delete(Objects.requireNonNull(stream), id.getValue());
        } catch (Throwable ex) {
            // if the server breaks down
            messageQueueIdempotentHandler.delMessageProcessed(id.toString());
            log.error("fail to record short link monitor statistic", ex);
            throw ex;
        }
        messageQueueIdempotentHandler.setAccomplished(id.toString());
    }

    public void actualSaveShortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO statsRecord) {
        fullShortUrl = Optional.ofNullable(fullShortUrl).orElse(statsRecord.getFullShortUrl());
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(String.format(LOCK_GID_UPDATE_KEY, fullShortUrl));
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        try {
            if (StrUtil.isBlank(gid)) {
                LambdaQueryWrapper<ShortLinkGotoDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkGotoDO.class)
                        .eq(ShortLinkGotoDO::getFullShortUrl, fullShortUrl);
                ShortLinkGotoDO shortLinkGotoDO = shortLinkGotoMapper.selectOne(queryWrapper);
                gid = shortLinkGotoDO.getGid();
            }
            Date date = new Date();
            int hourOfDay = LocalTime.now().getHour();
            int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
            LinkAccessStatsDO linkAccessStatsDO = LinkAccessStatsDO.builder()
                    .pv(1)
                    .uv(statsRecord.getUvFirstFlag() ? 1 : 0)
                    .uip(statsRecord.getUipFirstFlag() ? 1 : 0)
                    .hour(hourOfDay)
                    .weekday(dayOfWeek)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkAccessStatsMapper.shortLinkStats(linkAccessStatsDO);

            // IPInfo api revoke
            String actualProvince = "unknown";
            String actualCity = "unknown";
            IPinfo ipInfo = new IPinfo.Builder()
                    .setToken(statsLocaleIPInfoKey)
                    .build();
            try {
                IPResponse IPInfoResponse = ipInfo.lookupIP(statsRecord.getRemoteAddr());
                if (IPInfoResponse != null) {
                    boolean unknownFlag = IPInfoResponse.getBogon();
                    LinkLocaleStatsDO linkLocaleStatsDO = LinkLocaleStatsDO.builder()
                            .province(actualProvince = unknownFlag ? actualProvince : IPInfoResponse.getRegion())
                            .city(actualCity = unknownFlag ? actualCity : IPInfoResponse.getCity())
                            .postal(unknownFlag ? "unknown" : IPInfoResponse.getPostal())
                            .cnt(1)
                            .fullShortUrl(fullShortUrl)
                            .country("US")
                            .date(date)
                            .build();
                    linkLocaleStatsMapper.shortLinkLocaleState(linkLocaleStatsDO);
                }
            } catch (RateLimitedException ex) {
                throw new ServerException("IPInfo api went wrong");
            }
            // operating system
            LinkOsStatsDO linkOsStatsDO = LinkOsStatsDO.builder()
                    .os(statsRecord.getOs())
                    .cnt(1)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkOsStatsMapper.shortLinkOsState(linkOsStatsDO);
            // browser
            LinkBrowserStatsDO linkBrowserStatsDO = LinkBrowserStatsDO.builder()
                    .browser(statsRecord.getBrowser())
                    .cnt(1)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkBrowserStatsMapper.shortLinkBrowserState(linkBrowserStatsDO);
            // device
            LinkDeviceStatsDO linkDeviceStatsDO = LinkDeviceStatsDO.builder()
                    .device(statsRecord.getDevice())
                    .cnt(1)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkDeviceStatsMapper.shortLinkDeviceState(linkDeviceStatsDO);
            // network
            LinkNetworkStatsDO linkNetworkStatsDO = LinkNetworkStatsDO.builder()
                    .network(statsRecord.getNetwork())
                    .cnt(1)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkNetworkStatsMapper.shortLinkNetworkState(linkNetworkStatsDO);
            // logs
            LinkAccessLogsDO linkAccessLogsDO = LinkAccessLogsDO.builder()
                    .user(statsRecord.getUv())
                    .ip(statsRecord.getRemoteAddr())
                    .browser(statsRecord.getBrowser())
                    .os(statsRecord.getOs())
                    .network(statsRecord.getNetwork())
                    .device(statsRecord.getDevice())
                    .locale(StrUtil.join("-", "US", actualProvince, actualCity))
                    .fullShortUrl(fullShortUrl)
                    .build();
            linkAccessLogsMapper.insert(linkAccessLogsDO);
            // update pv, uv, uip
            shortLinkMapper.incrementStats(gid, fullShortUrl, 1, statsRecord.getUvFirstFlag() ? 1 : 0, statsRecord.getUipFirstFlag() ? 1 : 0);
            // today statistic
            // optimize: use a new boolean instead of uvFirstFlag
            // AtomicBoolean uvTodayFirstFlag = new AtomicBoolean();
            // Long uvTodayAdd = stringRedisTemplate.opsForSet().add("short-link:stats:uv:" + DateUtil.formatDate(date) + ":" + fullShortUrl, uv.get(), the time to the end of the day);
            // uvTodayFirstFlag.set(uvTodayAdd != null && uvTodayAdd > 0L);
            LinkStatsTodayDO linkStatsTodayDO = LinkStatsTodayDO.builder()
                    .todayPv(1)
                    .todayUv(statsRecord.getUvFirstFlag() ? 1 : 0)
                    .todayUip(statsRecord.getUipFirstFlag() ? 1 : 0)
                    .fullShortUrl(fullShortUrl)
                    .date(date)
                    .build();
            linkTodayStatsMapper.shortLinkTodayState(linkStatsTodayDO);
        } catch (Throwable ex) {
            log.error("short link access statistic exception", ex);
        } finally {
            rLock.unlock();
        }
    }
}