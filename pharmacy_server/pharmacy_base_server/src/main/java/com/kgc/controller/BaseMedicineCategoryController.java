package com.kgc.controller;


import com.kgc.entity.BaseMedicineCategory;
import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("base")
public class BaseMedicineCategoryController {
    @Autowired
    private BaseMedicineCategoryService categoryService;

    @RequestMapping(value = "/catlist")
    public Message list() {
        Message message = categoryService.listWithTree();
        return message;
    }

    @RequestMapping("/delCatById")
    public Message delCatById(Integer id) {
        Message message = categoryService.delCatById(id);
        return message;
    }
    @RequestMapping("/updateCat")
    public Message updateCat(@RequestBody BaseMedicineCategory baseMedicineCategory) {
        Message message = categoryService.updateCat(baseMedicineCategory);
        return message;
    }

    @RequestMapping("/addCat")
    public Message addUnit(@RequestBody BaseMedicineCategory baseMedicineCategory){
        Message message = categoryService.addCat(baseMedicineCategory);
        return message;
    }

    @RequestMapping("/checkCat")
    public Message checkCat(String name){
        Message message = categoryService.checkCatByName(name);
        return message;
    }
    @RequestMapping("/checkCatById")
    public Message checkCatById(Integer id){
        Message message = categoryService.checkCatById(id);
        return message;
    }
}