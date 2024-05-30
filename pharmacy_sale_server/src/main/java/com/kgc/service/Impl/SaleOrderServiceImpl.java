package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.*;
import com.kgc.entity.*;
import com.kgc.feign.*;
import com.kgc.service.SaleOrderService;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, XsOrder> implements SaleOrderService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private StockDetailFeign stockDetailFeign;
    @Autowired
    private SaleOutWarehouseFeign saleOutWarehouseFeign;
    @Autowired
    private KcOutintodetialFeign kcOutintodetialFeign;
    @Autowired
    private CwXsysFeign cwXsysFeign;
    @Autowired
    private CwAccountsFeign cwAccountsFeign;
    @Autowired
    private CwInvoiceFeign cwInvoiceFeign;


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
        Message saleOutWarehouseMessage=saleOutWarehouseFeign.addSaleOutWarehouse(kcSalefromware);
        //获取订单详情
        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        for(OrderMedicine orderMedicine:medicineDetailList){
            orderMedicine.setCode(xsOrder.getOrderNo());
            //生成销售明细单
            int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            //更新库存明细
            Message stockDetailMessage=stockDetailFeign.reduceStockDetailByMedicineId(orderMedicine.getMedicineId(),orderMedicine.getQuantity(),orderMedicine.getBatchCode());
            //生成销售出库明细单
            String code2=CodeUtil.createCode("CRKMX");
            KcOutintodetial kcOutintodetial=new KcOutintodetial();
            kcOutintodetial.setCode(code2);
            kcOutintodetial.setTypeId(5);
            kcOutintodetial.setCreateBy(1);
            kcOutintodetial.setCreateDate(xsOrder.getOrderDate());
            kcOutintodetial.setOrderCode(xsOrder.getOrderNo());
            kcOutintodetial.setMedicineId(orderMedicine.getMedicineId());
            kcOutintodetial.setToStockMoney(orderMedicine.getTotalPrice());
            kcOutintodetial.setBatchCode(orderMedicine.getBatchCode());
            kcOutintodetial.setToStockQuantity(orderMedicine.getQuantity());
            kcOutintodetial.setPrice(orderMedicine.getSalePrice());
            Message kcOutintodetialMessage=kcOutintodetialFeign.addKcOutintodetial(kcOutintodetial);
            if(count2<0&&"200".equals(stockDetailMessage.getCode())&&"200".equals(kcOutintodetialMessage.getCode())){
                result=false;
            }
        }
        //生成销售收款单
        CwXsys cwXsys=new CwXsys();
        String code3=CodeUtil.createCode("CWXSYS");
        cwXsys.setCode(code3);
        cwXsys.setOriginalOrder(xsOrder.getOrderNo());
        cwXsys.setCreateTime(xsOrder.getOrderDate());
        cwXsys.setCost(xsOrder.getTotalPrice());
        cwXsys.setCreateBy(1);
        Message cwXsysMessage=cwXsysFeign.addCwXsys(cwXsys);
        //生成销售应收流水
        CwAccounts cwAccounts=new CwAccounts();
        String code4=CodeUtil.createCode("XSYSLS");
        cwAccounts.setCode(code4);
        cwAccounts.setOrderCode(xsOrder.getOrderNo());
        cwAccounts.setCategoryId(3);
        cwAccounts.setCost(xsOrder.getTotalPrice());
        cwAccounts.setBankAcountId(xsOrder.getBankAccountId());
        cwAccounts.setDescription(xsOrder.getRemark());
        cwAccounts.setCreateTime(xsOrder.getOrderDate());
        cwAccounts.setCreateBy(1);
        Message cwAccountsMessage=cwAccountsFeign.insertCwAccounts(cwAccounts);
        //生成销售应收发票
        CwInvoice cwInvoice=new CwInvoice();
        String code5=CodeUtil.createCode("XSYSFP");
        cwInvoice.setCode(code5);
        cwInvoice.setCategoryId(3);
        cwInvoice.setOrderNumber(xsOrder.getOrderNo());
        cwInvoice.setCreateTime(xsOrder.getOrderDate());
        cwInvoice.setCreateBy(1);
        cwInvoice.setCost(xsOrder.getTotalPrice());
        Message cwInvoiceMessage=cwInvoiceFeign.addCwInvoice(cwInvoice);
        if(count>0&&"200".equals(saleOutWarehouseMessage.getCode())&&result&&"200".equals(cwXsysMessage.getCode())&&"200".equals(cwAccountsMessage.getCode())&&"200".equals(cwInvoiceMessage.getCode())){
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
        if(medicineDetailList!=null){
            for(OrderMedicine orderMedicine:medicineDetailList){
                orderMedicine.setCode(xsOrder.getOrderNo());
                int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
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
        //删除
       int count=saleOrderMapper.deleteSaleOrderByOrderNo(xsOrder.getOrderNo());
       int count2=saleOrderMapper.deleteSaleOrderDetailByOrderNo(xsOrder.getOrderNo());
       boolean result=false;
        if(count>0){
            xsOrder.setId(null);
            Message message1=addSaleOrder(xsOrder);
            if("200".equals(message1.getCode())){
                result=true;
            }
        }
        if(result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveUpdateSaleOrder(XsOrder xsOrder) {
        //删除
        int count=saleOrderMapper.deleteSaleOrderByOrderNo(xsOrder.getOrderNo());
        int count2=saleOrderMapper.deleteSaleOrderDetailByOrderNo(xsOrder.getOrderNo());
        boolean result=false;
        if(count>0){
            Message message1=saveSaleOrder(xsOrder);
            if("200".equals(message1.getCode())){
                result=true;
            }
        }
        if(result){
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
        if(count>0){
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

    @Override
    public void saleOrderExcel(XsOrder xsOrder, HttpServletResponse response) {
        List<XsOrder> xsOrderList=saleOrderMapper.getSaleOrderListByPage(xsOrder);
        for (XsOrder xsOrder1 :xsOrderList) {
            List<BaseMedicine> baseMedicineList = saleOrderMapper.getSaleOrderDetailByOrderNo(xsOrder1.getOrderNo());
            xsOrder1.setBaseMedicineList(baseMedicineList);
        }
        try {
            ExeclUtil.write(xsOrderList, XsOrder.class,response,"销售订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给销售订单添加退货标识
     * @param orderNo
     * @return
     */
    @Override
    public Message updateSaleOrderByOrderNo(String orderNo) {
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",orderNo);
        updateWrapper.set("isCheck", 1);
        int count=saleOrderMapper.update(null, updateWrapper);
        if(count>0) {
            return Message.success();
        }else{
            return Message.error();
        }
    }

    /**
     * 删除销售订单的退货标识
     * @param orderNo
     * @return
     */
    @Override
    public Message recoverSaleOrderByOrderNo(String orderNo) {
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",orderNo);
        updateWrapper.set("isCheck", 0);
        int count=saleOrderMapper.update(null, updateWrapper);
        if(count>0) {
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public List<BaseMedicine> getSaleOrderDetailByOrderNo(String orderNo) {
        List<BaseMedicine> baseMedicineDetailList=saleOrderMapper.getSaleOrderDetailByOrderNo(orderNo);
        if(baseMedicineDetailList!=null){
            return baseMedicineDetailList;
        }else {
            return null;
        }
    }


}
