package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.XsRefundtype;

public interface RefundTypeService  extends IService<XsRefundtype> {

    public Message getRefundTypeList();
}
