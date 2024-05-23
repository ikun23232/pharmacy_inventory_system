package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.RefundInWarehouseMapper;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.service.RefundInWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundInWarehouseServiceImpl extends ServiceImpl<RefundInWarehouseMapper, KcSalefromware> implements RefundInWarehouseService {

    @Autowired
    private RefundInWarehouseMapper refundInWarehouseMapper;
    @Override
    public Message getRefundInWarehouseListByPage(KcSalefromware kcSalefromware) {
        PageHelper.startPage(kcSalefromware.getCurrentPage(),5);
        List<KcSalefromware> kcSalefromwareList=refundInWarehouseMapper.getRefundInWarehouseListByPage(kcSalefromware);
        PageInfo<KcSalefromware> pageInfo=new PageInfo<>(kcSalefromwareList);
        if(pageInfo!=null) {
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }

    }
}
