//package com.kgc.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.kgc.dao.StoreHouseMapper;
//import com.kgc.entity.Message;
//import com.kgc.entity.Page;
//import com.kgc.entity.StoreHouse;
//import com.kgc.service.StoreHouseService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author 15279
// * @description 功能描述
// * @create 2024/5/7 9:28
// */
//@Service
//public class StorehouseServiceImpl implements StoreHouseService {
//    private Logger logger= LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private StoreHouseMapper storeHouseMapper;
//    @Override
//    public Message getStoreHouseList(String code, Page page) {
//        PageHelper.startPage(page.getPageNum(),page.getPageSize());
//        List<StoreHouse> storeHouseList = storeHouseMapper.getStoreHouseList(code);
//        PageInfo pageInfo = new PageInfo(storeHouseList);
//        return Message.success(pageInfo);
//    }
//
//    @Override
//    public Message deleteStorehouse(int id) {
//        int count = storeHouseMapper.deleteStorehouse(id);
//        if (count > 0){
//            return Message.success();
//        }
//        return Message.error("删除失败");
//    }
//
//    @Override
//    public Message checkName(String name) {
//        StoreHouse storeHouse = storeHouseMapper.checkName(name);
//        logger.debug("storeHouse:"+storeHouse);
//        System.out.println("storeHouse:"+storeHouse);
//        if (storeHouse != null){
//            return Message.success(storeHouse);
//        }
//        return Message.error("没有该仓库");
//    }
//
//    @Override
//    public Message addStoreHouse(StoreHouse storeHouse) {
////        boolean save = this.save(storeHouse);
////        if (save){
////            return Message.success();
////        }
//        return Message.error("添加失败");
//    }
//
//    @Override
//    public Message updateStoreHouse(StoreHouse storeHouse) {
////        boolean b = this.updateById(storeHouse);
////        if (b){
////            return Message.success();
////        }
//        return Message.error("修改失败");
//    }
//}
