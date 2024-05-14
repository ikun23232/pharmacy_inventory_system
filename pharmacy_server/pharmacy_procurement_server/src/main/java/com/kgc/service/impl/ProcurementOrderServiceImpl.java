package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.ProcurementOrderMapper;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.*;
import com.kgc.service.ProcurementOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 15:57
 */
@Service
@Transactional
public class ProcurementOrderServiceImpl extends ServiceImpl<ProcurementOrderMapper, CgddOrder> implements ProcurementOrderService {
    @Autowired
    private ProcurementOrderMapper mapper;
    @Autowired
    private PublicOMedicineMapper orderMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Message getCgddOrder(CgddOrder cgddOrder, Page page) {
        logger.debug("cgddOrder:"+cgddOrder);
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date beginDate = inputSdf.parse(cgddOrder.getBeginTime());
            Date endDate = inputSdf.parse(cgddOrder.getEndTime());
           cgddOrder.setBeginTime(outputSdf.format(beginDate));
           cgddOrder.setEndTime(outputSdf.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<CgddOrder> order = mapper.getCgddOrder(cgddOrder);
        PageInfo pageInfo = new PageInfo(order);
        if (order != null){
            return Message.success(pageInfo);
        }
        return Message.error("没有数据");
    }

    @Override
    public Message addCgddOrder(CgddOrder cgddOrder) {
        int count1 = 0;
        int num = 0;
        double price  =0.0;
        for (BaseMedicine baseMedicine: cgddOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgddOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalprice(baseMedicine.getTotalPrice());
            orderMedicine.setSourceCode(baseMedicine.getSourceCode());
            orderMedicine.setProviderId(cgddOrder.getProviderId());
            orderMedicine.setMedicineid(baseMedicine.getMedicineId());
            int temp = orderMapper.insert(orderMedicine);
            if (temp > 0){
                count1++;
                num += orderMedicine.getQuantity();
                price += orderMedicine.getTotalprice();
            }
        }
        if (cgddOrder.getMedicineList().size() != count1){
            return Message.error("添加失败");
        }
        cgddOrder.setCount(num);
        cgddOrder.setReferenceAmount(price);
        cgddOrder.setOrderStatus(2);
        int count = mapper.insert(cgddOrder);
        if (count > 0){
            return Message.success();
        }
        throw new RuntimeException("添加药品详细失败");
    }

    @Override
    public Message deleteById(int id) {
        int count = mapper.deleteById(id);
        if (count > 0){
            return Message.success();
        }
        return Message.error("删除失败！");
    }

    @Override
    public Message setVoidState(CgddOrder cgddOrder) {
        int count = mapper.updateById(cgddOrder);
        if (count >0){
            return Message.success();
        }
        return Message.error("作废失败！");
    }

    @Override
    public Message getCgddByCode(CgddOrder cgddOrder) {
        CgddOrder cgddByCode = mapper.getCgddByCode(cgddOrder);
        if (cgddByCode != null){
            return Message.success(cgddByCode);
        }
        return Message.error("该订单编号没有订单");
    }

    @Override
    public Message updateCgddById(CgddOrder cgddOrder) {
        int count1 = 0;
        int num = 0;
        double price  =0.0;
        for (BaseMedicine baseMedicine: cgddOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgddOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalprice(baseMedicine.getTotalPrice());
            orderMedicine.setSourceCode(baseMedicine.getCode());
            orderMedicine.setProviderId(cgddOrder.getProviderId());
            orderMedicine.setId(baseMedicine.getMedicineOrderId());
            int temp = orderMapper.updateById(orderMedicine);
            if (temp > 0){
                count1++;
                num += orderMedicine.getQuantity();
                price += orderMedicine.getTotalprice();
            }
        }
        if (cgddOrder.getMedicineList().size() != count1){
            return Message.error("修改失败");
        }
        cgddOrder.setCount(num);
        cgddOrder.setReferenceAmount(price);
        cgddOrder.setUpdateBy(1);
        cgddOrder.setUpdateTime(new Date());
        int count = mapper.updateById(cgddOrder);
        if (count > 0){
            return Message.success();
        }
        throw new RuntimeException("修改订单失败");
    }

    @Override
    public Message auditingOrder(CgddOrder cgddOrder) {
        if (cgddOrder.getApprovalStatus() == 1){
            cgddOrder.setEffectiveTime(new Date());
            cgddOrder.setIsPay(1);
            cgddOrder.setPayTime(new Date());
            cgddOrder.setOrderStatus(3);
        }
        cgddOrder.setApproverBy(1);
        int count = mapper.updateById(cgddOrder);
        if (count > 0){
            return Message.success();
        }
        return Message.error("审批失败");
    }
}
