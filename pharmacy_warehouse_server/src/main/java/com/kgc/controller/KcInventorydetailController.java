package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.annotation.Log;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.dao.KcInventorydetailMapper;
import com.kgc.entity.KcInventory;
import com.kgc.entity.KcInventorydetail;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.IKcInventoryService;
import com.kgc.service.IKcInventorydetailService;
import com.kgc.vo.KcInventoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-05-21
 */
@RestController
@RequestMapping("/warehouse")
public class KcInventorydetailController {




    @Autowired
    private IKcInventorydetailService iKcInventorydetailService;
    @Autowired
    private KcInventorydetailMapper kcInventorydetailMapper;
    @Autowired
    private KcInventoryMapper kcInventoryMapper;

    @Log("盘点详细信息修改")
    @RequestMapping("/check/updateCheck")
    public Message updateCheck(@RequestBody KcInventoryVo vo ) {
        Integer id = vo.getId();
        List<KcInventorydetail> kcInventorydetailList = vo.getKcInventorydetailList();
        boolean flag = false;
        vo.setUpdateTime(new Date());
        vo.setUpdateBy(1);
        kcInventoryMapper.update(vo, new UpdateWrapper<KcInventory>().eq("id", id));
        boolean b = kcInventorydetailMapper.deleteKcInventory(id);
        for (KcInventorydetail kcInventorydetail : kcInventorydetailList){
            flag = iKcInventorydetailService.updateCheck(kcInventorydetail, id);
        }
        return Message.success(flag);
    }

    @RequestMapping("/check/getKcInventoryUnitName")
    public Message getKcInventoryUnitName(Integer warehouseId,Integer medecineId) {
        Message kcInventoryUnitName = iKcInventorydetailService.getKcInventoryUnitName(warehouseId, medecineId);
        return kcInventoryUnitName;
    }
    @RequestMapping("/check/getKcInventoryDetailByIdwithother")
    public Message getKcInventoryDetailByIdwithother(Integer id) {
        Message kcInventoryUnitName = iKcInventorydetailService.getKcInventoryDetailByIdwithother(id);
        return kcInventoryUnitName;
    }

    @RequestMapping("/check/getAllKcInventoryDetailById")
    public Message getAllKcInventoryDetailById(Integer id) {
        Message kcInventory = iKcInventorydetailService.getAllKcInventoryDetailById(id);
        return kcInventory;
    }



}
