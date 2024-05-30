package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicineCategory;
import com.kgc.entity.Message;

/**
 * <p>
 * 服务类
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

    public Message getAllBaseCategory();
}