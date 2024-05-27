package com.kgc.controller;

import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-05-07 16:29
 **/
@RestController
@RequestMapping("/base")
public class UnitController {
    @Autowired
    private UnitService unitService;

    //查询全部计量单位
    @RequestMapping("/getUnitList")
    public Message getUnitList() {
        Message unitList = unitService.getUnitList();
        return unitList;
    }

    //分页查询查询全部计量单位
    @RequestMapping("/getUnitListByPage")
    public Message getUnitListByPage(int currentPageNo,int pageSize,String name) {
        Page page = new Page();
        page.setCurrentPageNo(currentPageNo);
        page.setPageSize(pageSize);
        Message unitListByPage = unitService.getUnitListByPage(page,name);
        return unitListByPage;
    }
    @RequestMapping("/delUnitById")
    public Message delUnitById(int id) {
        Message message = unitService.delUnitById(id);
        return message;
    }
    @RequestMapping("/updateUnit")
    public Message updateUnit(@RequestBody BaseUnit baseUnit) {
        Message message = unitService.updateUnit(baseUnit);
        return message;
    }
    @RequestMapping("/getUnitById")
    public Message getUnitById(int id) {
        Message unitById = unitService.getUnitById(id);
        return unitById;
    }
    @RequestMapping("/addUnit")
    public Message addUnit(@RequestBody BaseUnit baseUnit){
        Message message = unitService.addUnit(baseUnit);
        return message;
    }

    /**
     * 校验计量单位名称是否相同
     * @param unit
     * @return
     */
    @RequestMapping("/CheckUnit")
    public Message CheckUnit(@RequestParam(value = "unit",required = false)String unit,@RequestParam(value = "id",required = false) int id) {
        Message message = unitService.CheckUnit(unit, id);
        return message;
    }
}
