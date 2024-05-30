package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SysLoginLogMapper;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysLoginLog;
import com.kgc.service.SysLoginLogService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.SysLoginLogVo;
import com.kgc.vo.SysOperationLogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录日志 接口实现类
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;
    @Override
    public Message queryList(SysLoginLogVo entry, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        System.out.println(entry.getUserName());
        List<SysLoginLogVo> noticeList = sysLoginLogMapper.queryList(entry.getUserName(),entry.getOperationIp(),entry.getStartTime(),entry.getEndTime());
        PageInfo<SysLoginLogVo> pageInfo = new PageInfo<>(noticeList);
        return Message.success(pageInfo);
    }

    @Override
    public SysLoginLogVo queryById(Integer id) {
        return sysLoginLogMapper.queryById(id);
    }

    @Override
    public void LogLoginexcel(SysLoginLogVo sysLoginLogVo, HttpServletResponse response) {
        List<SysLoginLogVo> sysLoginLogVos = sysLoginLogMapper.queryList(null, null, null, null);
        for (SysLoginLogVo sysLoginLog : sysLoginLogVos){
            if (sysLoginLog.getResponseResult().length() > 255){
                sysLoginLog.setResponseResult(sysLoginLog.getResponseResult().substring(0, 254));
            }

        }
        try {
            ExeclUtil.write(sysLoginLogVos, SysLoginLogVo.class,response,"操作登陆日志信息");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
