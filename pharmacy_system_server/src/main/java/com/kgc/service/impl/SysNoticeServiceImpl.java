package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.*;
import com.kgc.dao.SysNoticeMapper;
import com.kgc.service.SysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.utils.ExeclUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {
    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public Message getNoticeList(String noticetitle, String startTime, String endTime, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<SysNotice> noticeList = sysNoticeMapper.getNoticeList(noticetitle,startTime,endTime);
        PageInfo<SysNotice> pageInfo = new PageInfo<>(noticeList);
        return Message.success(pageInfo);
    }

    @Override
    public Message existNotice(String noticetitle, Integer id) {
        SysNotice sysNotice = sysNoticeMapper.existNotice(noticetitle, id);
        if (sysNotice != null) {
            return Message.error("202","标题不为空",sysNotice);
        }
        return Message.success(sysNotice);
    }

    @Override
    public Message delUserById(Integer[] ids) {
        boolean flag = this.removeByIds(Arrays.asList(ids));
        if (flag ) {
            return Message.success(flag);
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateNotice(SysNotice sysNotice) {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken,null);
        sysNotice.setUpdateby(loginUser.getUserid());
        sysNotice.setUpdatedate(new Date());
        boolean flag = this.updateById(sysNotice);
        if (flag) {
            return Message.success(flag);
        }
        return Message.error("修改失败");
    }

    @Override
    public Message saveNotice(SysNotice sysNotice) {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken,null);
        sysNotice.setCreateby(loginUser.getUserid());
        sysNotice.setCreatedate(new Date());
        boolean flag = this.save(sysNotice);
        if (flag) {
            return Message.success(flag);
        }
        return Message.error("修改失败");
    }

    @Override
    public void Newsexcel(SysNotice sysNotice, HttpServletResponse response) {
        List<SysNotice> allUser = sysNoticeMapper.getNoticeList(null,null,null);
        try {
            ExeclUtil.write(allUser, SysNotice.class,response,"公告信息");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
