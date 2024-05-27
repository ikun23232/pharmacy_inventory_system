package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.*;
import com.kgc.service.IKcInventoryService;
import com.kgc.service.IKcInventorydetailService;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.NoticeTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 * 盘点单 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-05-20
 */
@RestController
@RequestMapping("/warehouse")
public class KcInventoryController {
    @Autowired
    private IKcInventoryService iKcInventoryService;
    @Autowired
    private IKcInventorydetailService iKcInventorydetailService;

    @RequestMapping("/check/getAllCheckByPage")
    public Message getAllCheckByPage(@RequestBody KcInventoryVo vo ) {
        int _currentPageNo = 1;

        if (vo.getCurrent() != null && !"".equals(vo.getCurrent())) {
            _currentPageNo = Integer.parseInt(vo.getCurrent());
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        Message message = iKcInventoryService.getAllCheckByPage(vo, page);
        return message;
    }

    @RequestMapping("/check/getKcMedicineByMedicine")
    public Message getKcMedicineByMedicine(Integer warehouseId,Integer materialCategoryId) {
        KcInventoryVo vo = new KcInventoryVo();
        vo.setMaterialCategoryId(materialCategoryId);
        vo.setWarehouseId(warehouseId);
        Message message = iKcInventoryService.getKcMedicineByMedicine(vo);
        return message;
    }

    @RequestMapping("/check/addCheck")
    public Message addCheck(@RequestBody KcInventoryVo kcInventory ) {
        Message message = iKcInventoryService.addCheck(kcInventory);
        return message;
    }

    @RequestMapping("/check/getKcInventoryVoById")
    public Message getKcInventoryVoById(Integer id ) {
        Message message = iKcInventoryService.getKcInventoryVoById(id);
        return message;
    }
    @RequestMapping("/check/updateorderStatus")
    public Message updateorderStatus(Integer id ) {
        Message message = iKcInventoryService.updateorderStatus(id);
        return message;
    }
    @RequestMapping("/check/updateisVoid")
    public Message updateisVoid(Integer id ) {
        Message message = iKcInventoryService.updateisVoid(id);
        return message;
    }
    @RequestMapping("/check/deleteCheck")
    public Message info(@RequestBody Integer[] ids) {

        iKcInventoryService.removeByIds(Arrays.asList(ids));
        UpdateWrapper<KcInventorydetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
        updateWrapper.set("isdel", 1);
        iKcInventorydetailService.update(updateWrapper);
        return Message.success("操作成功");
    }

    @RequestMapping("/check/approveCheck")
    public Message approveCheck(@RequestBody KcInventoryVo vo) {
        Message message = iKcInventoryService.approveCheck(vo);
        return message;
    }

    @RequestMapping("/check/checkExcel")
    public void checkExcel(@RequestBody KcInventoryVo vo, HttpServletResponse response) {
        iKcInventoryService.checkExcel(vo,response);
    }
}
