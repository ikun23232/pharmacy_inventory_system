package com.kgc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.RefundTypeMapper;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.entity.XsRefundtype;
import com.kgc.service.RefundTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundTypeServiceImpl extends ServiceImpl<RefundTypeMapper, XsRefundtype> implements RefundTypeService {

    @Autowired
    private RefundTypeMapper refundTypeMapper;

    @Override
    public Message getRefundTypeList() {
        List<XsRefundtype> refundtypeList=refundTypeMapper.selectList(null);
        if(refundtypeList!=null){
            return Message.success(refundtypeList);
        }else{
            return Message.error();
        }
    }
}
