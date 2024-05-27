package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseProviderService extends IService<BaseProvider> {

    public Message getBaseProviderList(String name,int type,int currentPageNo,int pageSize);

    public Message delBaseProvider(int id);

    public Message addBaseProvider(BaseProvider baseProvider);

    public Message updateBaseProvider(BaseProvider baseProvider);


    public Message getBaseProviderById(int id);

    public Message checkaddBaseProvider(String name);

    public Message checkupdateBaseProvider(String name,int id);

    public Message getAllBaseProvider();
    public Message getAllProvider();

    public Message getProviderByWareAndMe(Integer warehouseId, Integer medecineId);
}