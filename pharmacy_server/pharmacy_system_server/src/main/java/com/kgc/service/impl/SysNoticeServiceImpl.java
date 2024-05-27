package com.kgc.service.impl;

import com.kgc.entity.Message;
import com.kgc.entity.SysNotice;
import com.kgc.dao.SysNoticeMapper;
import com.kgc.service.SysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    @Autowired
    private SysNoticeMapper sysNoticeMapper;
    @Override
    public Message getSysNoticeList() {
        List<SysNotice> sysNoticeList=sysNoticeMapper.selectList(null);
        if(sysNoticeList!=null){
            return Message.success(sysNoticeList);
        }else{
            return Message.error();
        }
    }
}
