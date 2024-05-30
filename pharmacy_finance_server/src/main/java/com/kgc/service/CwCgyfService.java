package com.kgc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;

/**
 * (CwCgyf)表服务接口
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
public interface CwCgyfService extends IService<CwCgyf> {
    //获取采购应付列表
    Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize);
    //更新采购应付
    Message updateCwCgyf(CwCgyf cwCgyf);
    //根据code获取采购应付
    Message getCwCgyfByCode(String code);
    //根据id获取采购应付
    CwCgyf getCwCgyfById(int id);
    //获取供应商列表
    Message getProviderList();
    //根据订单号获取采购订单
    Message getCgddByCode(CgddOrder cgddOrder);
    //采购应付excel
    void cwCgyfExcel(HttpServletResponse response);
    Message addCgyf(CwCgyf cwCgyf);
}
