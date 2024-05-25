package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcOutintodetialMapper;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.dao.SaleOutWarehouseMapper;
import com.kgc.dao.StockDetailMapper;
import com.kgc.entity.*;
import com.kgc.service.KcOutintodetialService;
import com.kgc.service.SaleOrderService;
import com.kgc.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, XsOrder> implements SaleOrderService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private StockDetailMapper stockDetailMapper;
    @Autowired
    private SaleOutWarehouseMapper saleOutWarehouseMapper;
    @Autowired
    private KcOutintodetialMapper kcOutintodetialMapper;

    @Override
    public Message getSaleOrderListByPage(XsOrder xsOrder) {
        Message message=new Message();
        int currentPage= xsOrder.getCurrentPage();
        PageHelper.startPage(currentPage,5);
        List<XsOrder> xsOrderList=saleOrderMapper.getSaleOrderListByPage(xsOrder);
        PageInfo<XsOrder> pageInfo=new PageInfo<>(xsOrderList);
        if(pageInfo!=null){
            message.setCode("200");
            message.setData(pageInfo);
        }
        return message;
    }

    @Override
    public Message addSaleOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setType(0);
        xsOrder.setUpdateDate(xsOrder.getOrderDate());
        xsOrder.setEditStatus(1);
        //添加销售订单
        int count=saleOrderMapper.insert(xsOrder);
        //生成销售出库单
        String code= CodeUtil.createCode("XXCK");
        KcSalefromware kcSalefromware=new KcSalefromware();
        kcSalefromware.setCode(code);
        kcSalefromware.setOrderNo(xsOrder.getOrderNo());
        int saleOutCount=saleOutWarehouseMapper.insert(kcSalefromware);
        //生成销售出库明细单
        String code2=CodeUtil.createCode("CRKMX");
        KcOutintodetial kcOutintodetial=new KcOutintodetial();
        kcOutintodetial.setCode(code2);
        kcOutintodetial.setTypeid(5);
        kcOutintodetial.setCreateby(1);
        kcOutintodetial.setCreatedate(xsOrder.getOrderDate());
        kcOutintodetial.setOrdercode(xsOrder.getOrderNo());
        //获取订单详情
        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        for(OrderMedicine orderMedicine:medicineDetailList){
            orderMedicine.setCode(xsOrder.getOrderNo());
            //生成销售明细单
            int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            //更新库存明细
            int stockDetailCount=stockDetailMapper.reduceStockDetailByMedicineId(orderMedicine.getMedicineId(),orderMedicine.getQuantity());
            //添加出入库明细
            kcOutintodetial.setMedicineid(orderMedicine.getMedicineId());
            kcOutintodetial.setTostockmoney(orderMedicine.getTotalPrice());
            kcOutintodetial.setBatchCode(orderMedicine.getBatchCode());
            kcOutintodetial.setTostockquantity(orderMedicine.getQuantity());
            kcOutintodetial.setPrice(orderMedicine.getSalePrice());
            int count3=kcOutintodetialMapper.insert(kcOutintodetial);
            //生成销售收款单
            //生成销售应收流水
            //生成销售应收发票



            if(count2<0&&stockDetailCount<0&&count3<0){
                result=false;
            }
        }
        if(count>0&&saleOutCount>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveSaleOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setType(0);
        xsOrder.setUpdateDate(xsOrder.getOrderDate());
        xsOrder.setEditStatus(0);
        int count=saleOrderMapper.insert(xsOrder);
        boolean result=true;
        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        for(OrderMedicine orderMedicine:medicineDetailList){
            orderMedicine.setCode(xsOrder.getOrderNo());
            int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            if(count2<0){
                result=false;
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }
    @Override
    public Message getSaleOrderByOrderNo(String orderNo) {
        Message message=new Message();
        XsOrder xsOrder=saleOrderMapper.getSaleOrderByOrderNo(orderNo);
        List<BaseMedicine> baseMedicineDetailList=saleOrderMapper.getSaleOrderDetailByOrderNo(orderNo);
        xsOrder.setBaseMedicineList(baseMedicineDetailList);
        if(xsOrder!=null){
            message.setCode("200");
            message.setData(xsOrder);
        }
        return message;
    }

    @Override
    public Message updateSaleOrder(XsOrder xsOrder) {
        xsOrder.setEditStatus(1);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=saleOrderMapper.update(xsOrder, updateWrapper);
//        int count=saleOrderMapper.updateSaleOrder(xsOrder);

        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        if(medicineDetailList!=null){
            for(OrderMedicine orderMedicine:medicineDetailList){
                orderMedicine.setCode(xsOrder.getOrderNo());
                int count2=saleOrderMapper.updateSaleOrderDetail(orderMedicine);
                if(count2<0){
                    result=false;
                }
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveUpdateSaleOrder(XsOrder xsOrder) {
        xsOrder.setEditStatus(0);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=saleOrderMapper.update(xsOrder, updateWrapper);
//        int count=saleOrderMapper.updateSaleOrder(xsOrder);

        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        if(medicineDetailList!=null){
            for(OrderMedicine orderMedicine:medicineDetailList){
                orderMedicine.setCode(xsOrder.getOrderNo());
                int count2=saleOrderMapper.updateSaleOrderDetail(orderMedicine);
                if(count2<0){
                    result=false;
                }
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message deleteSaleOrder(String orderNo) {
        QueryWrapper<XsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("orderNo",orderNo);
        int count=saleOrderMapper.delete(queryWrapper);
        int count2=saleOrderMapper.deleteOrderDetailByOrderNo(orderNo);
        if(count>0&&count2>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message cancelSaleOrder(String orderNo) {
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",orderNo);
        updateWrapper.set("cancelStatus", 1);
        int count=saleOrderMapper.update(null, updateWrapper);
        int count2=saleOrderMapper.cancelOrderDetailByOrderNo(orderNo);
        if(count>0&&count2>0){
            return Message.success();
        }else{
            return Message.error();
        }

    }


}
