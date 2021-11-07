package me.codeboy.clipboard.controller;

import me.codeboy.clipboard.common.CommonResult;
import me.codeboy.clipboard.service.BackupService;
import me.codeboy.clipboard.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据控制器
 * Created by yuedong.li on 2019-07-12
 */
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BackupService backupService;

    @RequestMapping(value = "/api/getContents")
    public CommonResult getContents(@RequestParam(required = false, defaultValue = "share") String room, @RequestParam(required = false) String pwd) {
        return CommonResult.success(roomService.getContents(room), roomService.getContentUpdateTimestamp(room));
    }

    //0 普通文本  1图片
    @RequestMapping(value = "/api/addContent")
    public CommonResult addContent(@RequestParam(required = false, defaultValue = "share") String room, @RequestParam String content, @RequestParam int type) {
        boolean result = roomService.addContent(room, content, type);
        if (result) {
            backupService.backup();
        }
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/api/getContentVersion")
    public CommonResult getContentVersion(@RequestParam(required = false, defaultValue = "share") String room) {
        return CommonResult.success("success", roomService.getContentUpdateTimestamp(room));
    }
}
