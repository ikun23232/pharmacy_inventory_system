package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.dao.ProcurementOrderMapper;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.*;
import com.kgc.service.ProcurementOrderService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CgddVO;
import com.kgc.vo.MedicineVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private BaseMedicineMapper baseMedicineMapper;
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
        BigDecimal price = BigDecimal.ZERO;
        for (BaseMedicine baseMedicine: cgddOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgddOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setSourceCode(baseMedicine.getSourceCode());
            orderMedicine.setProviderId(cgddOrder.getProviderId());
            orderMedicine.setMedicineid(baseMedicine.getMedicineId());
            int temp = orderMapper.insert(orderMedicine);
            if (temp > 0){
                count1++;
                num += orderMedicine.getQuantity();
                price = orderMedicine.getTotalPrice().add(orderMedicine.getTotalPrice());
            }
        }
        if (cgddOrder.getMedicineList().size() != count1){
            return Message.error("添加失败");
        }
        cgddOrder.setCount(num);
        cgddOrder.setReferenceAmount(price);
        if (cgddOrder.getIsSave() == 1){
            cgddOrder.setOrderStatus(2);
        }else {
            cgddOrder.setOrderStatus(1);
        }
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
        BigDecimal price  =BigDecimal.ZERO;
        Map<String,Object> map = new HashMap<>();
        map.put("code",cgddOrder.getCode());
        int i = orderMapper.deleteByMap(map);
        if (i == 0){
            return Message.error("删除订单药品详情失败！");
        }
        for (BaseMedicine baseMedicine: cgddOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgddOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getMedicineId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setSourceCode(baseMedicine.getSourceCode());
            orderMedicine.setProviderId(cgddOrder.getProviderId());
            orderMedicine.setId(baseMedicine.getMedicineOrderId());
            int temp = orderMapper.insert(orderMedicine);
            if (temp > 0){
                count1++;
                num += orderMedicine.getQuantity();
                price = orderMedicine.getTotalPrice().add(orderMedicine.getTotalPrice());
            }
        }
        if (cgddOrder.getMedicineList().size() != count1){
            throw new RuntimeException("添加药品失败！");
        }
        cgddOrder.setCount(num);
        cgddOrder.setReferenceAmount(price);
        cgddOrder.setUpdateBy(1);
        cgddOrder.setUpdateTime(new Date());
        if (cgddOrder.getIsSave() == 1){
            cgddOrder.setOrderStatus(2);
        }else {
            cgddOrder.setOrderStatus(1);
        }        int count = mapper.updateById(cgddOrder);
        if (count > 0){
            return Message.success();
        }
        throw new RuntimeException("修改订单失败");
    }

    @Override
    public Message auditingOrder(CgddOrder cgddOrder) {
        cgddOrder.setEffectiveTime(new Date());
        cgddOrder.setApproverBy(1);
        cgddOrder.setApproverRemark(cgddOrder.getApproverRemark());
        if (cgddOrder.getApprovalStatus() == 2){
            if (cgddOrder.getPayType() == 2){
                cgddOrder.setIsPay(1);
                //添加流水
            }
            cgddOrder.setPayTime(new Date());
            cgddOrder.setOrderStatus(3);
        }else {
            cgddOrder.setOrderStatus(4);
        }
        int count = mapper.updateById(cgddOrder);
        if (count > 0){
            return Message.success();
        }
        return Message.error("审批失败");
    }

    @Override
    public void cgddExcel(CgddOrder cgddOrder, HttpServletResponse response) {
        List<CgddVO> order = mapper.imExcel();
        List<CgddVO> temp = new ArrayList<>();
        for (CgddVO cgddVO :order) {
            List<MedicineVO> medicineListByCode = baseMedicineMapper.getMedicineVOListByCode(cgddVO.getCode());
            cgddVO.setMedicineList(medicineListByCode);
            temp.add(cgddVO);
        }
        try {
            ExeclUtil.write(temp, CgddVO.class,response,"采购订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public BigDecimal getReferenceAmountByCode(String code) {
        BigDecimal referenceAmountById = mapper.getReferenceAmountByCode(code);
        return referenceAmountById;
    }

    @Override
    public int updateCgddIsPayByCode(String code) {
        int isPay= mapper.updateCgddIsPayByCode(code);
        return isPay;
    }

    @Override
    public Message addcgyf(CwCgyf cwCgyf) {
        String originalOrder = "";
//                cwCgyf.getOriginalOrder();
        BigDecimal referenceAmountByCode = mapper.getReferenceAmountByCode(originalOrder);
        if (referenceAmountByCode == null){
            return Message.error("该订单编号没有订单");
        }
        cwCgyf.setCost(referenceAmountByCode);
        String code = UUID.randomUUID().toString().replace("-", "");
        cwCgyf.setCode(code);
        int isAdd = mapper.addcgyf(cwCgyf);
        int isPay=this.updateCgddIsPayByCode(originalOrder);
        if (isPay <= 0){
            return Message.error("付款失败");
        }
        if (isAdd > 0){
            return Message.success();
        }

        return  Message.error("付款失败");
    }
}
