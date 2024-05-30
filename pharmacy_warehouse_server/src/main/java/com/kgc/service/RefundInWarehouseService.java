package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RefundInWarehouseService extends IService<KcSalefromware> {

    public Message getRefundInWarehouseListByPage(KcSalefromware kcSalefromware);

    public void refundInWarehouseExcel(KcSalefromware kcSalefromware, HttpServletResponse response);

    public Message addRefundInWarehouse(KcSalefromware kcSalefromware);

}
