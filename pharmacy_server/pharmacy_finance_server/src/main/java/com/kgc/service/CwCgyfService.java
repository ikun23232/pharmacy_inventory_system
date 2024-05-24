package com.kgc.service;


import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;

/**
 * (CwCgyf)表服务接口
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
public interface CwCgyfService {
    //获取采购应付列表
    Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize);
    //获取供应商列表
    Message getProviderList();
    //根据订单号获取采购订单
    Message getCgddByCode(CgddOrder cgddOrder);

}
