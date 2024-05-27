package org.zhenhaochen.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zhenhaochen.shortlink.admin.dao.entity.GroupDO;
import org.zhenhaochen.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.zhenhaochen.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * Group Api Layer
 */
public interface GroupService extends IService<GroupDO> {

    /**
     *  add new group
     * @param groupName group name
     */
    void saveGroup(String groupName);

    /**
     * query group
     * @return list of group
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * update group
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    /**
     * delete group
     */
    void deleteGroup(String gid);
}
