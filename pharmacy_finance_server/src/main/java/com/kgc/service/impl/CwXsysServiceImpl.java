package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.CwXsysMapper;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import com.kgc.feign.SaleOrderFeign;
import com.kgc.service.CwXsysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CwXsysServiceImpl extends ServiceImpl<CwXsysMapper, CwXsys> implements CwXsysService {

    @Autowired
    private CwXsysMapper cwXsysMapper;




    @Override
    public Message addCwXsys(CwXsys cwXsys) {
        int count=cwXsysMapper.insert(cwXsys);
        if(count>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
