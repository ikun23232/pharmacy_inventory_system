package com.kgc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CgsqOrderMapper;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.*;
import com.kgc.service.CgsqOrderService;
import com.kgc.utils.BigDecimalUtils;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CgVO;
import com.kgc.vo.CgddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
@Transactional
public class CgsqOrderServiceImpl extends ServiceImpl<CgsqOrderMapper, CgsqOrder> implements CgsqOrderService {
    @Autowired
    private CgsqOrderMapper cgsqOrderMapper;
    @Autowired
    private PublicOMedicineMapper orderMapper;

    @Override
    public Message getCgsqOrderList(CgVO vo) {
//        CgVO vo = JSON.parseObject(JSON.toJSONString(map), CgVO.class);

//        String code = JSON.parseObject(JSON.toJSONString(map.get("code")), String.class);
//        int subject = JSON.parseObject(JSON.toJSONString(map.get("subject")), Integer.class);
//        int type = JSON.parseObject(JSON.toJSONString(map.get("type")), Integer.class);
//        String startTime = JSON.parseObject(JSON.toJSONString(map.get("startTime")), String.class);
//        String endTime = JSON.parseObject(JSON.toJSONString(map.get("endTime")), String.class);
//        int voidState = JSON.parseObject(JSON.toJSONString(map.get("voidState")), Integer.class);
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("subject", vo.getSubject());
        paramsMap.put("type", vo.getType());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("voidState", vo.getVoidState());
        paramsMap.put("endTime", vo.getEndTime());
        paramsMap.put("approvalStatus", vo.getApprovalStatus());
        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<CgsqOrder> cgsqOrderList = cgsqOrderMapper.getCgsqOrderList(paramsMap);
        PageInfo<CgsqOrder> pageInfo = new PageInfo<>(cgsqOrderList);
        return Message.success(pageInfo);
    }

    @Override
    public Message getCgsqOrderByStates(CgVO vo) {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("subject", vo.getSubject());
        paramsMap.put("type", vo.getType());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("endTime", vo.getEndTime());
        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<CgsqOrder> cgsqOrderList = cgsqOrderMapper.getCgsqOrderByStates(paramsMap);
        PageInfo<CgsqOrder> pageInfo = new PageInfo<>(cgsqOrderList);
        return Message.success(pageInfo);
    }

    @Override
    public Message delCgsqOrderById(int id) {
        int updateRow = cgsqOrderMapper.deleteById(id);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message addCgsqOrder(CgsqOrder cgsqOrder) {
//          cgsqOrder.
        cgsqOrder.setDemandtime(new Date());
        List<BaseMedicine> medicineList = cgsqOrder.getMedicineList();
        int count=0;

        BigDecimal referencCount = BigDecimal.ZERO;
        for (BaseMedicine baseMedicine : medicineList) {
            count += baseMedicine.getQuantity();
            BigDecimal quantity = BigDecimal.valueOf(baseMedicine.getQuantity()); // 数量转为BigDecimal
            BigDecimal purchasePrice =baseMedicine.getPurchasePrice(); // 单价转为BigDecimal
            BigDecimal multiply = quantity.multiply(purchasePrice); // 使用BigDecimal的multiply方法进行精确乘法计算
            referencCount = referencCount.add(multiply); // 使用BigDecimal的add方法进行精确加法计算
        }
        cgsqOrder.setCount(count);
        cgsqOrder.setReferenceamount(referencCount.doubleValue());
        if (cgsqOrder.getOrderstatus()!=null){
            cgsqOrder.setOrderstatus(cgsqOrder.getOrderstatus());

        }else {
            cgsqOrder.setOrderstatus(1);
        }
        cgsqOrder.setDemanderby(1);
        cgsqOrder.setVoidstate(0);
        cgsqOrderMapper.insert(cgsqOrder);
        for (BaseMedicine baseMedicine : cgsqOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgsqOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setProviderId(baseMedicine.getProviderId());
            orderMapper.insert(orderMedicine);
        }
        return Message.success();
    }

    @Override
    public Message updateCgsqOrder(CgsqOrder cgsqOrder) {

        List<BaseMedicine> medicineList = cgsqOrder.getMedicineList();
        int count=0;

        BigDecimal referencCount = BigDecimal.ZERO;
        for (BaseMedicine baseMedicine : medicineList) {
            count += baseMedicine.getQuantity();
            BigDecimal quantity = new BigDecimal(baseMedicine.getQuantity()); // 数量转为BigDecimal
            BigDecimal purchasePrice = baseMedicine.getPurchasePrice(); // 单价转为BigDecimal
            BigDecimal multiply = quantity.multiply(purchasePrice); // 使用BigDecimal的multiply方法进行精确乘法计算
            referencCount = referencCount.add(multiply); // 使用BigDecimal的add方法进行精确加法计算
        }
        cgsqOrder.setCount(count);
        cgsqOrder.setReferenceamount(referencCount.doubleValue());
        if (cgsqOrder.getOrderstatus()!=null){
            cgsqOrder.setOrderstatus(cgsqOrder.getOrderstatus());
        }
        cgsqOrder.setUpdatetime(new Date());
        cgsqOrder.setUpdateby(1);
        cgsqOrderMapper.updateById(cgsqOrder);


        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("code", cgsqOrder.getCode());
        // 调用 deleteByMap 方法，传入 Map 对象删除满足条件的数据
        orderMapper.deleteByMap(columnMap);
        for (BaseMedicine baseMedicine : cgsqOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgsqOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setProviderId(baseMedicine.getProviderId());
            orderMapper.insert(orderMedicine);
        }
        return Message.success();
    }

    @Override
    public Message getCgsqOrder(int id) {
        CgsqOrder cgSqOrderById = cgsqOrderMapper.getCgSqOrderById(id);
        return Message.success(cgSqOrderById);
    }

    @Override
    public Message voidCgsqOrder(int id) {
        UpdateWrapper<CgsqOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("voidState", 1)
                .eq("id", id); // 添加ID的条件
        int updateRow = cgsqOrderMapper.update(null, updateWrapper);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("作废失败");
    }

    @Override
    public Message approveCgsqOrder(int id,String approveRemark,int approveMent) {
        CgsqOrder cgsqOrder=new CgsqOrder();
        cgsqOrder.setId(id);
        cgsqOrder.setApprovalstatus(approveMent);
        cgsqOrder.setEffectivetime(new Date());
        cgsqOrder.setApproverremark(approveRemark);
        cgsqOrder.setOrderstatus(3);

        int approverBy=1;
        cgsqOrder.setApproverby(approverBy);
        int updateRow = cgsqOrderMapper.updateById(cgsqOrder);

        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("审核失败");
    }

    @Override
    public Message getCgsqOrderByCode(CgsqOrder cgsqOrder) {
        CgsqOrder cgsqOrderByCode = cgsqOrderMapper.getCgsqOrderByCode(cgsqOrder);
        return Message.success(cgsqOrderByCode);
    }

    @Override
    public Message getCgsqOrderListByExcel() {
        List<CgsqOrder> cgsqOrderListByExcel = cgsqOrderMapper.getCgsqOrderListByExcel(new HashMap<>());
        return Message.success(cgsqOrderListByExcel);
    }

    @Override
    public void cgsqExcel(CgsqOrder cgsqOrder, HttpServletResponse response) {
        List<CgsqOrder> cgsqOrderListByExcel = cgsqOrderMapper.getCgsqOrderListByExcel(new HashMap<>());
        try {
            ExeclUtil.write(cgsqOrderListByExcel, CgsqOrder.class,response,"采购申请订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
