package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.StoreHouseMapper;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.StoreHouse;
import com.kgc.service.StoreHouseService;
import org.springframework.stereotype.Service;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 9:28
 */
@Service
public class StorehouseServiceImpl extends ServiceImpl<StoreHouseMapper, StoreHouse> implements StoreHouseService {
    @Override
    public Message getStoreHouseList(String code, Page page) {

        return null;
    }

    @Override
    public Message deleteStorehouse(int id) {
        return null;
    }

    @Override
    public Message checkName(String name) {
        return null;
    }

    @Override
    public Message addStoreHouse(StoreHouse storeHouse) {
        return null;
    }

    @Override
    public Message updateStoreHouse(StoreHouse storeHouse) {
        return null;
    }
}
