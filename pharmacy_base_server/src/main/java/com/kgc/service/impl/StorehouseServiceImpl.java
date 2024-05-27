package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.StoreHouseMapper;
import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.StoreHouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 9:28
 */
@Service
public class StorehouseServiceImpl extends ServiceImpl<StoreHouseMapper, BaseStorehouse> implements StoreHouseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StoreHouseMapper storeHouseMapper;

    @Override
    public Message getStoreHouseList(String code, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<BaseStorehouse> storeHouseList = storeHouseMapper.getStoreHouseList(code);
        PageInfo pageInfo = new PageInfo(storeHouseList);
        return Message.success(pageInfo);
    }

    @Override
    public Message deleteStorehouse(int id) {
        int count = storeHouseMapper.deleteStorehouse(id);
        if (count > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message checkName(String name) {
        BaseStorehouse storeHouse = storeHouseMapper.checkName(name);
        if (storeHouse != null) {
            return Message.success(storeHouse);
        }
        return Message.error("没有该仓库");
    }

    @Override
    public Message addStoreHouse(BaseStorehouse storeHouse) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        String code = "CW-" + date;
        storeHouse.setCode(code);
        storeHouse.setCreateTime(new Date());
        int count = storeHouseMapper.insert(storeHouse);
        if (count > 0) {
            return Message.success();
        }
        return Message.error("添加失败");
    }

    @Override
    public Message updateStoreHouse(BaseStorehouse storeHouse) {
        storeHouse.setUpdateTime(new Date());
        int count = storeHouseMapper.updateById(storeHouse);
        if (count > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message getStoreHouseById(int id) {
        BaseStorehouse storeHouse = storeHouseMapper.selectById(id);
        if (storeHouse != null) {
            return Message.success(storeHouse);
        }
        return Message.error("没有该用户");
    }

    @Override
    public Message getAllStoreHouseList() {
        List<BaseStorehouse> storeHouseList = storeHouseMapper.getStoreHouseList(null);
        if (storeHouseList.isEmpty()) {
            return Message.error("没有数据");
        }
        return Message.success(storeHouseList);
    }
}