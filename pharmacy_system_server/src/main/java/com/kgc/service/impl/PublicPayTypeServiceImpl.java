package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.PublicPayTypeMapper;
import com.kgc.entity.CgType;
import com.kgc.entity.Message;
import com.kgc.service.PublicPayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 17:01
 */
@Service
public class PublicPayTypeServiceImpl extends ServiceImpl<PublicPayTypeMapper, CgType> implements PublicPayTypeService {

    @Autowired
    private PublicPayTypeMapper publicPayTypeMapper;
    @Override
    public Message getPayType() {
        List<CgType> cgTypes = publicPayTypeMapper.selectList(null);
        return Message.success(cgTypes);
    }
}
