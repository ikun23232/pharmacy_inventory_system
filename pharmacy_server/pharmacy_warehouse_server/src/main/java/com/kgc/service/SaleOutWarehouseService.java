package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SaleOutWarehouseService extends IService<KcSalefromware> {

    public Message getSaleOutWarehouseListByPage(KcSalefromware kcSalefromware);

    public Message deleteSaleOutWarehouseOrder(String code);

    public void saleOutWarehouseExcel(KcSalefromware kcSalefromware, HttpServletResponse response);

}
