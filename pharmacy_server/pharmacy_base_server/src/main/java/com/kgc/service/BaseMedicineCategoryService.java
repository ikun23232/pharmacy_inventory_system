package com.kgc.service;

import com.kgc.entity.BaseMedicineCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseMedicineCategoryService extends IService<BaseMedicineCategory> {
    public Message listWithTree();

    public Message delCatById(int id);
    public Message updateCat(BaseMedicineCategory baseMedicineCategory);

    public Message addCat(BaseMedicineCategory baseMedicineCategory);
    public Message checkCatByName(String name);
    public Message checkCatById(Integer id);
}