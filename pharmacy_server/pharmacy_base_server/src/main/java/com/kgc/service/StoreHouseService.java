package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.Message;
import com.kgc.entity.Page;

/**
 * @author 15279
 * @description
 * @create 2024/5/7 9:13
 */
public interface StoreHouseService extends IService<BaseStorehouse> {
    public Message getStoreHouseList(String code, Page page);
    public Message deleteStorehouse(int id);
    public Message checkName(String name);
    public Message addStoreHouse(BaseStorehouse storeHouse);
    public Message updateStoreHouse(BaseStorehouse storeHouse);
    public Message getStoreHouseById(int id);
}
