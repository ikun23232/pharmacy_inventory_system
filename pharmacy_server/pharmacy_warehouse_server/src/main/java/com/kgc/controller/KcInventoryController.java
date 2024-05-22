package com.kgc.controller;


import com.kgc.entity.KcInventory;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.IKcInventoryService;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.NoticeTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
