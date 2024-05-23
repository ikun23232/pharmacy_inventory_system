package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;

import java.util.List;

public interface RefundInWarehouseService extends IService<KcSalefromware> {

    public Message getRefundInWarehouseListByPage(KcSalefromware kcSalefromware);

}
