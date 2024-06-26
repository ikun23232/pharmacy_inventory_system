//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CgrkOrderMapper;
import com.kgc.dao.KcMedicineMapper;
import com.kgc.dao.KcOutintodetialMapper;
import com.kgc.entity.*;
import com.kgc.feign.CGDDFegin;
import com.kgc.feign.PublicOMedicineService;
import com.kgc.service.CgrkOrderService;
import com.kgc.utils.CodeUtil;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CgVO;
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

@Service
@Transactional
public class CgrkOrderServiceImpl extends ServiceImpl<CgrkOrderMapper, CgrkOrder> implements CgrkOrderService {
    @Autowired
    private CgrkOrderMapper cgrkOrderMapper;
    @Autowired
    private PublicOMedicineService medicineService;
    @Autowired
    private KcMedicineMapper kcMedicineMapper;
    @Autowired
    private CGDDFegin cgddFegin;

    @Autowired
    private KcOutintodetialMapper kcOutintodetialMapper;
    @Override
    public Message getCgrkOrderService(CgVO vo) {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("isPay", vo.getIsPay());
        paramsMap.put("subject", vo.getSubject());
        paramsMap.put("providerId", vo.getProviderId());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("voidState", vo.getVoidState());
        paramsMap.put("endTime", vo.getEndTime());
        paramsMap.put("approvalStatus", vo.getApprovalStatus());
        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<CgrkOrder> cgrkOrderList = cgrkOrderMapper.getCgrqOrderList(paramsMap);
        PageInfo<CgrkOrder> pageInfo = new PageInfo<>(cgrkOrderList);
        return Message.success(pageInfo);
    }

    @Override
    public Message delCgrkOrderById(int id) {
        int updateRow = cgrkOrderMapper.deleteById(id);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message addCgrkOrder(CgrkOrder cgrqOrder) {
        cgrqOrder.setCreateTime(new Date());
        List<BaseMedicine> medicineList = cgrqOrder.getMedicineList();
        int count=0;

        BigDecimal referencCount = BigDecimal.ZERO;
        for (BaseMedicine baseMedicine : medicineList) {
            count += baseMedicine.getQuantity();
            BigDecimal quantity = new BigDecimal(baseMedicine.getQuantity()); // 数量转为BigDecimal
            BigDecimal purchasePrice = baseMedicine.getPurchasePrice(); // 单价转为BigDecimal
            BigDecimal multiply = quantity.multiply(purchasePrice); // 使用BigDecimal的multiply方法进行精确乘法计算
            referencCount = referencCount.add(multiply); // 使用BigDecimal的add方法进行精确加法计算
        }
        cgrqOrder.setCount(count);
        cgrqOrder.setMedicineprice(referencCount.doubleValue());
        if (cgrqOrder.getOrderStatus()>0){
            cgrqOrder.setOrderStatus(cgrqOrder.getOrderStatus());
        }else {
            cgrqOrder.setOrderStatus(1);
        }
        cgrqOrder.setApprovalstatus(0);

        SysUser loginUser = (SysUser) StpUtil.getSession().get("user");
        cgrqOrder.setDocumenterby(loginUser.getUserid());
        cgrqOrder.setVoidstate(0);
        cgrqOrder.setCreateby(loginUser.getUserid());
        cgrqOrder.setIsaddwarehouse(0);
//        cgrqOrder.setPayMoney(referencCount);
//        cgrqOrder.setFormMoney(BigDecimal.ZERO);
         cgrkOrderMapper.insert(cgrqOrder);
        for (BaseMedicine baseMedicine : cgrqOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgrqOrder.getCode());
            orderMedicine.setSourceCode(baseMedicine.getSourceCode());
            orderMedicine.setMedicineId(baseMedicine.getMedicineId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setProviderId(baseMedicine.getProviderId());
            orderMedicine.setFowardWarHouseId(baseMedicine.getFowardWarHouseId());
            Message message = medicineService.selectMaxYourField();
            orderMedicine.setBatchCode(String.valueOf(Integer.valueOf(message.getData().toString())+1));
            medicineService.addMedicineOrder(orderMedicine);
        }
        return Message.success();

    }

    @Override
    public Message updateCgrkOrder(CgrkOrder cgrqOrder) {
        List<BaseMedicine> medicineList = cgrqOrder.getMedicineList();
        int count=0;
        cgrqOrder.setCount(count);
        if (cgrqOrder.getOrderStatus()>1){
            cgrqOrder.setOrderStatus(cgrqOrder.getOrderStatus());
        }
        cgrqOrder.setUpdateTime(new Date());
        SysUser loginUser = (SysUser) StpUtil.getSession().get("user");

        cgrqOrder.setUpdateby(loginUser.getUserid());
        cgrkOrderMapper.updateById(cgrqOrder);


        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("code", cgrqOrder.getCode());
        // 调用 deleteByMap 方法，传入 Map 对象删除满足条件的数据
        medicineService.deleteMediciOrder(columnMap);
        for (BaseMedicine baseMedicine : cgrqOrder.getMedicineList()) {

            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgrqOrder.getCode());
            orderMedicine.setSourceCode(baseMedicine.getSourceCode());
            orderMedicine.setMedicineId(baseMedicine.getMedicineId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalPrice(baseMedicine.getTotalPrice());
            orderMedicine.setProviderId(baseMedicine.getProviderId());
            orderMedicine.setFowardWarHouseId(baseMedicine.getFowardWarHouseId());
            Message message = medicineService.selectMaxYourField();
            orderMedicine.setBatchCode(String.valueOf(Integer.valueOf(message.getData().toString())+1));
            medicineService.addMedicineOrder(orderMedicine);
        }
        return Message.success();
    }

    @Override
    public Message getCgrqOrder(int id) {
        CgrkOrder cgrkOrder = cgrkOrderMapper.getCgrkOrder(id);
        return Message.success(cgrkOrder);
    }

    @Override
    public Message voidCgrqOrder(int id) {
        UpdateWrapper<CgrkOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("voidState", 1)
                .eq("id", id); // 添加ID的条件
        int updateRow = cgrkOrderMapper.update(null, updateWrapper);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("作废失败");
    }

    @Override
    public Message approveCgrqOrder(int id, String approveRemark, int approveMent) {
// 创建一个只包含需要更新字段的对象
        CgrkOrder updateObj = new CgrkOrder();
// 假设你想要更新的字段是这些
        updateObj.setEffectiveTime(new Date());
        SysUser loginUser = (SysUser) StpUtil.getSession().get("user");

        updateObj.setApproverby(loginUser.getUserid());
        updateObj.setApprovalstatus(approveMent);
        CgrkOrder cgrkOrder = cgrkOrderMapper.getCgrkOrder(id);

        if (approveMent==2){
            updateObj.setOrderStatus(3);
            updateObj.setIsaddwarehouse(1);

            //更新我的流水
            List<BaseMedicine> medicineList = cgrkOrder.getMedicineList();
            for (BaseMedicine baseMedicine : medicineList) {
                KcMedicine kcMedicine=new KcMedicine();
                kcMedicine.setBatchCode(baseMedicine.getBatchCode());
                kcMedicine.setStorehouseId(baseMedicine.getFowardWarHouseId());
                kcMedicine.setMedicineId(baseMedicine.getMedicineId());
                kcMedicine.setProviderId(baseMedicine.getProviderId());
                kcMedicine.setQuantity(baseMedicine.getQuantity());
                kcMedicine.setMoney(baseMedicine.getPurchasePrice());
                kcMedicine.setTotalPrice(baseMedicine.getTotalPrice());
                kcMedicineMapper.insert(kcMedicine);
            }

            for (BaseMedicine baseMedicine : cgrkOrder.getMedicineList()) {
                KcOutintodetial kcOutintodetial = new KcOutintodetial();
                kcOutintodetial.setTypeId(3);
                String crkmxCode = CodeUtil.createCode("CRKMX");
                kcOutintodetial.setCode(crkmxCode);
                kcOutintodetial.setCreateDate(new Date());
                kcOutintodetial.setOrderCode(cgrkOrder.getCode());
                kcOutintodetial.setMedicineId(baseMedicine.getMedicineId());
                kcOutintodetial.setProviderId(baseMedicine.getProviderId());
                kcOutintodetial.setToStockQuantity(baseMedicine.getQuantity());
                kcOutintodetial.setToStockMoney(baseMedicine.getTotalPrice());
                kcOutintodetial.setPrice(baseMedicine.getPurchasePrice());
                kcOutintodetial.setWareHouseId(baseMedicine.getFowardWarHouseId());
                kcOutintodetial.setBatchCode(baseMedicine.getBatchCode());
                kcOutintodetialMapper.insert(kcOutintodetial);
            }

        }else {
            updateObj.setOrderStatus(2);
            updateObj.setIsaddwarehouse(0);

        }
        int updateRow = cgrkOrderMapper.approveOrder(id,updateObj.getEffectiveTime(),updateObj.getApproverby(),approveMent,updateObj.getOrderStatus(),updateObj.getIsaddwarehouse());


        //变更采购订单的单据状态变为4
        CgddOrder cgddOrder = new CgddOrder();
        cgddOrder.setOrderStatus(4);
        cgddOrder.setId(cgrkOrder.getPurchaseid());
        Message message = cgddFegin.updateCgddIsPayById(cgddOrder);
        if (!message.getCode().equals("200")){
            throw new RuntimeException("更新采购订单状态失败!");
        }
        if (message.getCode().equals("200")){
            return Message.success();
        }
        return Message.error("审核失败");
    }

    @Override
    public void cgrkExcel(CgrkOrder cgrkOrder, HttpServletResponse response) {
        List<CgrkOrder> order = cgrkOrderMapper.getCgrkOrderByExcel(new HashMap<String, Object>());
        try {
            ExeclUtil.write(order,CgrkOrder.class,response,"采购入库");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
