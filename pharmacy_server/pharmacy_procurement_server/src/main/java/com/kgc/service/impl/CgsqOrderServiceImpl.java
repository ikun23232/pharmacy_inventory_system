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
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Message delCgsqOrderById(int id) {
        int updateRow = cgsqOrderMapper.deleteById(id);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message addCgsqOrder(CgsqOrder cgsqOrder) {

        cgsqOrderMapper.insert(cgsqOrder);
        for (BaseMedicine baseMedicine : cgsqOrder.getMedicineList()) {
            OrderMedicine orderMedicine = new OrderMedicine();
            orderMedicine.setCode(cgsqOrder.getCode());
            orderMedicine.setMedicineid(baseMedicine.getId());
            orderMedicine.setQuantity(baseMedicine.getQuantity());
            orderMedicine.setTotalprice(baseMedicine.getTotalPrice());
            orderMapper.insert(orderMedicine);

        }

        return Message.error("添加订单失败");


    }

    @Override
    public Message updateCgsqOrder(CgsqOrder cgsqOrder) {
        return null;
    }

    @Override
    public Message getCgsqOrder(int id) {
        return null;
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


}
