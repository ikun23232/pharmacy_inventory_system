//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CgrkOrderMapper;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import com.kgc.service.CgrkOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PublicOMedicineMapper orderMapper;
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
            BigDecimal purchasePrice = new BigDecimal(String.valueOf(baseMedicine.getPurchasePrice())); // 单价转为BigDecimal
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
        cgrqOrder.setDocumenterby(1);
        cgrqOrder.setVoidstate(0);
        cgrqOrder.setCreateby(1);
        cgrkOrderMapper.insert(cgrqOrder);
        for (BaseMedicine baseMedicine : cgrqOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgrqOrder.getCode());
            orderMedicine.setSourceCode(cgrqOrder.getSourceCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
//            orderMedicine.setTotalprice(baseMedicine.getTotalPrice());
//            orderMedicine.setProviderId(baseMedicine.getProviderId());
//            orderMedicine.setFowardWarHouseId(baseMedicine.getFowardWarHouseId());
            Integer batchCode = orderMapper.selectMaxYourField();
//            orderMedicine.setBatchCode((batchCode+1)+"");
            orderMapper.insert(orderMedicine);
        }
        return Message.success();

    }

    @Override
    public Message updateCgrkOrder(CgrkOrder cgrqOrder) {
        return null;
    }

    @Override
    public Message getCgrqOrder(int id) {

        return null;
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
        CgrkOrder cgsqOrder=new CgrkOrder();
        cgsqOrder.setId(id);
        cgsqOrder.setApprovalstatus(1);
        cgsqOrder.setEffectiveTime(new Date());
        cgsqOrder.setApproverremark(approveRemark);
//        cgsqOrder.setpprovalstatus(3);

//批准人
        int approverBy=1;
        cgsqOrder.setApproverby(approverBy);
        int updateRow = cgrkOrderMapper.updateById(cgsqOrder);

        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("审核失败");
    }
}
