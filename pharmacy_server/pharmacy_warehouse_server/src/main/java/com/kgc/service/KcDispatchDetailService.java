package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcDispatchdetails;
import com.kgc.entity.Message;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/20 14:46
 */
public interface KcDispatchDetailService extends IService<KcDispatchdetails> {
    public Message getKcDetailsList(String dispatchCode);
}
