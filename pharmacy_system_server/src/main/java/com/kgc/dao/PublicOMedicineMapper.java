package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.OrderMedicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 11:45
 */
@Mapper
public interface PublicOMedicineMapper extends BaseMapper<OrderMedicine> {
    @Select("SELECT MAX(batchCode) FROM  order_medicine")
    Integer selectMaxYourField();

}
