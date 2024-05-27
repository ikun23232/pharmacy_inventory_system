package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseStorehouse;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 8:51
 */
public interface StoreHouseMapper extends BaseMapper<BaseStorehouse> {
    public List<BaseStorehouse> getStoreHouseList(String code);

    public int deleteStorehouse(int id);

    public BaseStorehouse checkName(String name);
}