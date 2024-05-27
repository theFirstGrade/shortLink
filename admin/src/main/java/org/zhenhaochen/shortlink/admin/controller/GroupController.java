package org.zhenhaochen.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.common.convention.result.Results;
import org.zhenhaochen.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.zhenhaochen.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.zhenhaochen.shortlink.admin.service.GroupService;

import java.util.List;

/**
 * Group Management Controller
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * add new group
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * query group list
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * update group name
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * delete grop
     */
    @DeleteMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestParam("gid") String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

}
