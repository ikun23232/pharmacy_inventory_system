package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.StoreHouse;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 8:51
 */
public interface StoreHouseMapper {
    public List<StoreHouse> getStoreHouseList(String code);
    public int deleteStorehouse(int id);
    public StoreHouse checkName(String name);
}
