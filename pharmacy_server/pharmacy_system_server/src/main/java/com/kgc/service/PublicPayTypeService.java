package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.dao.PublicPayTypeMapper;
import com.kgc.entity.CgType;
import com.kgc.entity.Message;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 16:59
 */
public interface PublicPayTypeService extends IService<CgType> {
    public Message getPayType();
}
