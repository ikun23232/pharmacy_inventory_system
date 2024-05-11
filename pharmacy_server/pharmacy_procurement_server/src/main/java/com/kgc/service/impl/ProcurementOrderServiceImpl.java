package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.ProcurementOrderMapper;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.*;
import com.kgc.entity.*;
import com.kgc.service.ProcurementOrderService;
import com.kgc.service.PublicOMedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<CgddOrder> order = mapper.getCgddOrder(cgddOrder);
        PageInfo pageInfo = new PageInfo(order);
        if (order != null){
            return Message.success(pageInfo);
        }
        return Message.error("没有数据");
    }

    @Override
    public Message addCgddOrder(CgddOrder cgddOrder) {
        int count = mapper.insert(cgddOrder);
        int count1 = 0;
        if (count > 0){
            for (BaseMedicine baseMedicine: cgddOrder.getMedicineList()) {
                OrderMedicine orderMedicine = new OrderMedicine();
                orderMedicine.setCode(cgddOrder.getCode());
                orderMedicine.setMedicineid(baseMedicine.getId());
                orderMedicine.setQuantity(baseMedicine.getQuantity());
                orderMedicine.setTotalprice(baseMedicine.getTotalPrice());
                int temp = orderMapper.insert(orderMedicine);
                if (temp > 0){
                    count1++;
                }
            }
        }
        return Message.error("添加订单失败");
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
    public Message getCgPayCom(String year, String month) {
        List<CgPayCom> cgPayCom = mapper.getCgPayCom(year, month);
        if (cgPayCom != null){
            return Message.success(cgPayCom);
        }
        return Message.error("没有数据");
    }

    @Override
    public Message getCgPayNum(String year) {
        List<CgPayNum> cgPayNum = mapper.getCgPayNum(year);
        if (cgPayNum != null){
            return Message.success(cgPayNum);
        }
        return Message.error("没有数据");
    }

    @Override
    public Message getCgPayNumList(String year,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CgPayNum> cgPayNum = mapper.getCgPayNum(year);
        PageInfo pageInfo = new PageInfo(cgPayNum);
        if (cgPayNum != null){
            return Message.success(pageInfo);
        }
        return Message.error("没有数据");
    }
}
