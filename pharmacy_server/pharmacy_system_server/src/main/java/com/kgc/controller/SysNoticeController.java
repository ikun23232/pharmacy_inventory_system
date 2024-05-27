package com.kgc.controller;


import com.kgc.annotation.Log;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysNotice;
import com.kgc.entity.SysUser;
import com.kgc.service.SysNoticeService;
import com.kgc.vo.NoticeTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/user")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping("/sysNotice/getNoticeList")
    public Message getNoticeList(@RequestBody NoticeTitle noticeTitle ) {
        int _currentPageNo = 1;

        if (noticeTitle.getCurrent() != null && !"".equals(noticeTitle.getCurrent())) {
            _currentPageNo = Integer.parseInt(noticeTitle.getCurrent());
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        Message message = sysNoticeService.getNoticeList(noticeTitle.getNoticeTitle(), noticeTitle.getStartTime(), noticeTitle.getEndTime(), page);
        return message;
    }

    @Log("根据id获取公告")
    @RequestMapping("/sysNotice/info/{id}")
    public Message info(@PathVariable("id") Integer id) {

        SysNotice byId = sysNoticeService.getById(id);
        if (byId == null) {
            return Message.error("没有此资讯");
        }
        return Message.success(byId);
    }
    @Log("公告导出")
    @RequestMapping("/sysNotice/Newsexcel")
    public void Newsexcel(@RequestBody SysNotice sysNotice , HttpServletResponse response) {

        sysNoticeService.Newsexcel(sysNotice, response);

    }

    @RequestMapping("/sysNotice/existNotice")
    public Message existNotice(@RequestParam("noticetitle") String noticetitle, @RequestParam("id") Integer id) {
        Message message = sysNoticeService.existNotice(noticetitle, id);
        return message;
    }
    @Log("根据id删除公告")
    @RequestMapping("/sysNotice/delUserById")
    public Message delUnitById(@RequestBody Integer[] ids) {
        Message message = sysNoticeService.delUserById(ids);
        return message;
    }
    @Log("修改公告")
    @RequestMapping("/sysNotice/updateNotice")
    public Message updateNotice(@RequestBody SysNotice sysNotice) {
        Message message = sysNoticeService.updateNotice(sysNotice);
        return message;
    }
    @Log("添加公告")
    @RequestMapping("/sysNotice/saveNotice")
    public Message saveNotice(@RequestBody SysNotice sysNotice) {
        sysNoticeService.saveNotice(sysNotice);
        return Message.success(sysNotice);
    }
}

