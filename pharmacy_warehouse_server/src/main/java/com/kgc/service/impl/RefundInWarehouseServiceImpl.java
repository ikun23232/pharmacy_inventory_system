package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.RefundInWarehouseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.feign.SaleOrderFeign;
import com.kgc.service.RefundInWarehouseService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.KcRefundInWareVo;
import com.kgc.vo.KcSalefromwareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class RefundInWarehouseServiceImpl extends ServiceImpl<RefundInWarehouseMapper, KcSalefromware> implements RefundInWarehouseService {

    @Autowired
    private RefundInWarehouseMapper refundInWarehouseMapper;
    @Autowired
    private SaleOrderFeign saleOrderFeign;

    @Override
    public Message getRefundInWarehouseListByPage(KcSalefromware kcSalefromware) {
        PageHelper.startPage(kcSalefromware.getCurrentPage(),5);
        List<KcSalefromware> kcSalefromwareList=refundInWarehouseMapper.getRefundInWarehouseListByPage(kcSalefromware);
        PageInfo<KcSalefromware> pageInfo=new PageInfo<>(kcSalefromwareList);
        if(pageInfo!=null) {
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }

    }

    @Override
    public void refundInWarehouseExcel(KcSalefromware kcSalefromware, HttpServletResponse response) {
        List<KcRefundInWareVo> KcRefundInWareList=refundInWarehouseMapper.getRefundInWarehouseList(kcSalefromware);
        for (KcRefundInWareVo KcRefundInWareVo :KcRefundInWareList) {
            Message message = saleOrderFeign.getSaleOrderDetailByOrderNo(KcRefundInWareVo.getOrderNo());
            List<BaseMedicine> baseMedicineList=(List<BaseMedicine>) message.getData();
            KcRefundInWareVo.setBaseMedicineList(baseMedicineList);
        }
        try {
            ExeclUtil.write(KcRefundInWareList, KcRefundInWareVo.class,response,"销售退货入库订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addRefundInWarehouse(KcSalefromware kcSalefromware) {
        int count=refundInWarehouseMapper.insert(kcSalefromware);
        if(count>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
