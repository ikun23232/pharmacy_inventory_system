package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StockDetailService extends IService<BaseMedicine> {

    public Message getStockDetailListByPage(BaseMedicine baseMedicine);

    public Message getStockDetailList();

    public void stockDetailExcel(BaseMedicine baseMedicine,HttpServletResponse response);

    public Message reduceStockDetailByMedicineId(int medicineId,int quantity,String batchCode);

    public Message addStockDetailByMedicineId(int medicineId,int quantity,String batchCode);



}
