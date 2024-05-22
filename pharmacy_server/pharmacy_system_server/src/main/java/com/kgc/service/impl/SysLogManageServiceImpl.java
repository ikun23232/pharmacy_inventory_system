package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysLogManage;
import com.kgc.dao.SysLogManageMapper;
import com.kgc.entity.SysNotice;
import com.kgc.service.SysLogManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.vo.SysOperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysLogManageServiceImpl extends ServiceImpl<SysLogManageMapper, SysLogManage> implements SysLogManageService {
    @Autowired
    private SysLogManageMapper sysLogManageMapper;

    @Override
    public Message queryList(SysOperationLogVo entry, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<SysOperationLogVo> noticeList = sysLogManageMapper.queryList(entry.getUsername(),entry.getIp(),entry.getTitle(),entry.getStartTime(),entry.getEndTime());
        PageInfo<SysOperationLogVo> pageInfo = new PageInfo<>(noticeList);
        return Message.success(pageInfo);
    }

    @Override
    public SysOperationLogVo queryById(Integer id) {
        return sysLogManageMapper.queryById(id);
    }
}
