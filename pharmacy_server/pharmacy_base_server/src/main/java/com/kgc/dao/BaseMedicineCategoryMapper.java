package com.kgc.dao;

import com.kgc.entity.BaseMedicineCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Mapper
public interface BaseMedicineCategoryMapper extends BaseMapper<BaseMedicineCategory> {
    List<BaseMedicineCategory> getAllCategoryList();
    BaseMedicineCategory checkCatByName(String name);
    BaseMedicineCategory checkCatById(Integer id);
    List<BaseMedicineCategory> checkCatByIdLst(Integer parentId);

}