package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.StockDetailMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.service.StockDetailService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.MedicineStockVo;
import com.kgc.vo.RefundOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class StockDetailServiceImpl extends ServiceImpl<StockDetailMapper, BaseMedicine> implements StockDetailService {

    @Autowired
    private  StockDetailMapper stockDetailMapper;
    @Override
    public Message getStockDetailListByPage(BaseMedicine baseMedicine) {
        PageHelper.startPage(baseMedicine.getCurrentPage(),5);
        List<BaseMedicine> stockDetailList=stockDetailMapper.getStockDetailListByPage(baseMedicine);
        PageInfo<BaseMedicine> pageInfo=new PageInfo<>(stockDetailList);
        if(pageInfo!=null) {
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getStockDetailList() {
        List<BaseMedicine> stockDetailList=stockDetailMapper.getStockDetailListByPage();
        if(stockDetailList!=null) {
            return Message.success(stockDetailList);
        }else{
            return Message.error();
        }
    }

    @Override
    public void stockDetailExcel(BaseMedicine baseMedicine,HttpServletResponse response) {
        List<MedicineStockVo> stockDetailList=stockDetailMapper.getStockDetailList(baseMedicine);
        try {
            ExeclUtil.write(stockDetailList, MedicineStockVo.class,response,"销售退货订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message reduceStockDetailByMedicineId(int medicineId, int quantity, String batchCode) {
        int count= stockDetailMapper.reduceStockDetailByMedicineId(medicineId,quantity,batchCode);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }

    }

    @Override
    public Message addStockDetailByMedicineId(int medicineId, int quantity, String batchCode) {
        int count= stockDetailMapper.addStockDetailByMedicineId(medicineId,quantity,batchCode);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }
}
