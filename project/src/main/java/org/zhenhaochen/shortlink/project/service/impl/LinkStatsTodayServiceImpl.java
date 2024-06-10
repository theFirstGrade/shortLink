package org.zhenhaochen.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zhenhaochen.shortlink.project.dao.entity.LinkStatsTodayDO;
import org.zhenhaochen.shortlink.project.dao.mapper.LinkStatsTodayMapper;
import org.zhenhaochen.shortlink.project.service.LinkStatsTodayService;

/**
 * 短链接今日统计接口实现层 Short Link Today Statistic Interface Implementation
 */
@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}
