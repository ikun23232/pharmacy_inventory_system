package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.*;
import com.kgc.entity.*;
import com.kgc.feign.*;
import com.kgc.remote.KcReportedRemote;
import com.kgc.service.RefundOrderService;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.RefundOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, XsOrder> implements RefundOrderService {

    @Autowired
    private RefundOrderMapper refundOrderMapper;
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private KcReportedFeign kcReportedFeign;
    @Autowired
    private KcReporteddetailFeign kcReporteddetailFeign;
    @Autowired
    private StockDetailFeign stockDetailFeign;
    @Autowired
    private RefundInWarehouseFeign refundInWarehouseFeign;
    @Autowired
    private KcOutintodetialFeign kcOutintodetialFeign;
    @Autowired
    private CwXstkFeign cwXstkFeign;
    @Autowired
    private CwAccountsFeign cwAccountsFeign;
    @Autowired
    private CwInvoiceFeign cwInvoiceFeign;

    @Override
    public Message getRefundOrderListByPage(XsOrder xsOrder) {
        int currentPage= xsOrder.getCurrentPage();
        PageHelper.startPage(currentPage,5);
        List<XsOrder> xsOrderList=refundOrderMapper.getRefundOrderListByPage(xsOrder);
        PageInfo<XsOrder> pageInfo=new PageInfo<>(xsOrderList);
        if(pageInfo!=null){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message addRefundOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setUpdateDate(new Date());
        xsOrder.setType(1);
        xsOrder.setEditStatus(1);
        xsOrder.setIsCheck(0);
        int count=refundOrderMapper.insert(xsOrder);
        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        boolean result=true;
        for(OrderMedicine orderMedicine:checkedDetail){
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
    public Message saveRefundOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setUpdateDate(new Date());
        xsOrder.setType(1);
        xsOrder.setEditStatus(0);
        int count=refundOrderMapper.insert(xsOrder);
        boolean result=true;
        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        if(checkedDetail!=null){
            for(OrderMedicine orderMedicine:checkedDetail){
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
    public Message updateRefundOrder(XsOrder xsOrder) {
        xsOrder.setUpdateBy(1);
        xsOrder.setUpdateDate(new Date());
        xsOrder.setEditStatus(1);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=refundOrderMapper.update(xsOrder, updateWrapper);
        //删除
        int count2=saleOrderMapper.deleteSaleOrderDetailByOrderNo(xsOrder.getOrderNo());
        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        for(OrderMedicine orderMedicine:medicineDetailList){
            orderMedicine.setCode(xsOrder.getOrderNo());
            int count3=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            if(count3<0){
                result=false;
            }
        }
        if(result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveUpdateRefundOrder(XsOrder xsOrder) {
        xsOrder.setUpdateBy(1);
        xsOrder.setUpdateDate(new Date());
        xsOrder.setEditStatus(0);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=refundOrderMapper.update(xsOrder, updateWrapper);
        //删除
        int count2=saleOrderMapper.deleteSaleOrderDetailByOrderNo(xsOrder.getOrderNo());
        List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
        boolean result=true;
        for(OrderMedicine orderMedicine:medicineDetailList){
            orderMedicine.setCode(xsOrder.getOrderNo());
            int count3=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            if(count3<0){
                result=false;
            }
        }
        if(result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message checkedRefundOrder(XsOrder xsOrder) {
        if(xsOrder.getIsCheck()==1){
            xsOrder.setEditStatus(2);
        }
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=refundOrderMapper.update(xsOrder, updateWrapper);
        if(count>0){
            if(xsOrder.getIsCheck()==1){
                //获取订单详情
                List<OrderMedicine> medicineDetailList=xsOrder.getMedicineDetailList();
                //判断订单是否报损
                if(xsOrder.getRefundTypeId()==1||xsOrder.getRefundTypeId()==2){
                    //添加报损申请单
                    KcReported kcReported=new KcReported();
                    String reportedCode=CodeUtil.createCode("THBS");
                    kcReported.setCode(reportedCode);
                    kcReported.setStorehouseId(1);
                    kcReported.setReportedTypeId(2);
                    kcReported.setDocumenterBy(1);
                    kcReported.setCreateBy(1);
                    kcReported.setCreateTime(xsOrder.getOrderDate());
                    Message kcReportedMessage=kcReportedFeign.insertKcReported(kcReported);
                    for(OrderMedicine orderMedicine:medicineDetailList){
                        //添加报损明细单
                        KcReporteddetail kcReporteddetail=new KcReporteddetail();
                        kcReporteddetail.setReportedCode(reportedCode);
                        kcReporteddetail.setMedicineId(orderMedicine.getMedicineId());
                        kcReporteddetail.setQuantity(orderMedicine.getQuantity());
                        kcReporteddetail.setBatchCode(orderMedicine.getBatchCode());
                        Message kcReporteddetailMessage=kcReporteddetailFeign.insertKcReporteddetail(kcReporteddetail);
                    }
                }else{
                    //生成销售退货入库单
                    String code= CodeUtil.createCode("XSTHRK");
                    KcSalefromware kcSalefromware=new KcSalefromware();
                    kcSalefromware.setCode(code);
                    kcSalefromware.setOrderNo(xsOrder.getOrderNo());
                    Message refundInWarehouseMessage=refundInWarehouseFeign.addRefundInWarehouse(kcSalefromware);
                }
                boolean result=true;
                for(OrderMedicine orderMedicine:medicineDetailList){
                    if(xsOrder.getRefundTypeId()!=1&&xsOrder.getRefundTypeId()!=2){
                        //更新库存明细
                        Message stockDetailMessage=stockDetailFeign.addStockDetailByMedicineId(orderMedicine.getMedicineId(),orderMedicine.getQuantity(),orderMedicine.getBatchCode());
                        //生成退货入库明细单
                        String code2=CodeUtil.createCode("XSTHRKMX");
                        KcOutintodetial kcOutintodetial=new KcOutintodetial();
                        kcOutintodetial.setCode(code2);
                        kcOutintodetial.setTypeId(6);
                        kcOutintodetial.setCreateBy(1);
                        kcOutintodetial.setCreateDate(xsOrder.getOrderDate());
                        kcOutintodetial.setOrderCode(xsOrder.getOrderNo());
                        kcOutintodetial.setMedicineId(orderMedicine.getMedicineId());
                        kcOutintodetial.setToStockMoney(orderMedicine.getTotalPrice());
                        kcOutintodetial.setBatchCode(orderMedicine.getBatchCode());
                        kcOutintodetial.setToStockQuantity(orderMedicine.getQuantity());
                        kcOutintodetial.setPrice(orderMedicine.getSalePrice());
                        Message kcOutintodetialMessage=kcOutintodetialFeign.addKcOutintodetial(kcOutintodetial);
//                        if(stockDetailMessage<0&&count3<0){
//                            result=false;
//                        }
                    }
                }
                //生成销售退款付款单
                CwXstk cwXstk=new CwXstk();
                String code3=CodeUtil.createCode("CWXSTK");
                cwXstk.setCode(code3);
                cwXstk.setOriginalOrder(xsOrder.getOrderNo());
                cwXstk.setCreateTime(xsOrder.getOrderDate());
                cwXstk.setCost(xsOrder.getTotalPrice());
                cwXstk.setCreateBy(1);
                Message cwXstkMessage=cwXstkFeign.addCwXstk(cwXstk);
                //生成销售退货应付流水
                CwAccounts cwAccounts=new CwAccounts();
                String code4=CodeUtil.createCode("XSTHYFLS");
                cwAccounts.setCode(code4);
                cwAccounts.setOrderCode(xsOrder.getOrderNo());
                cwAccounts.setCategoryId(4);
                cwAccounts.setCost(xsOrder.getTotalPrice());
                cwAccounts.setBankAcountId(xsOrder.getBankAccountId());
                cwAccounts.setDescription(xsOrder.getRemark());
                cwAccounts.setCreateTime(xsOrder.getOrderDate());
                cwAccounts.setCreateBy(1);
                Message cwAccountsMessage=cwAccountsFeign.insertCwAccounts(cwAccounts);
                //生成销售退货应付发票
                CwInvoice cwInvoice=new CwInvoice();
                String code5=CodeUtil.createCode("XSTHYFFP");
                cwInvoice.setCode(code5);
                cwInvoice.setCategoryId(4);
                cwInvoice.setOrderNumber(xsOrder.getOrderNo());
                cwInvoice.setCreateTime(xsOrder.getOrderDate());
                cwInvoice.setCreateBy(1);
                cwInvoice.setCost(xsOrder.getTotalPrice());
                Message cwInvoiceMessage=cwInvoiceFeign.addCwInvoice(cwInvoice);
            }
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public void refundOrderExcel(XsOrder xsOrder, HttpServletResponse response) {
        List<RefundOrderVo> refundOrderVoList=refundOrderMapper.getRefundOrderList(xsOrder);
        for (RefundOrderVo refundOrderVo :refundOrderVoList) {
            List<BaseMedicine> baseMedicineList = saleOrderMapper.getSaleOrderDetailByOrderNo(refundOrderVo.getOrderNo());
            refundOrderVo.setBaseMedicineList(baseMedicineList);
        }
        try {
            ExeclUtil.write(refundOrderVoList, RefundOrderVo.class,response,"销售退货订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
