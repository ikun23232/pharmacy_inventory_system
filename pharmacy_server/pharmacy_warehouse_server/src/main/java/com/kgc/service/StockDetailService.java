package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;

import java.util.List;

public interface StockDetailService extends IService<BaseMedicine> {

    public Message getStockDetailListByPage(BaseMedicine baseMedicine);

    public Message getStockDetailList();
}
