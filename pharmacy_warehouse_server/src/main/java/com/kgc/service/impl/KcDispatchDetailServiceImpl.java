package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.KcDispatchDetailMapper;
import com.kgc.entity.KcDispatchdetails;
import com.kgc.entity.Message;
import com.kgc.service.KcDispatchDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/21 8:52
 */
@Service
public class KcDispatchDetailServiceImpl extends ServiceImpl<KcDispatchDetailMapper, KcDispatchdetails> implements KcDispatchDetailService {

    @Autowired
    private KcDispatchDetailMapper kcDispatchDetailMapper;
    @Override
    public Message getKcDetailsList(String dispatchCode) {
        List<KcDispatchdetails> kcDetailsList = kcDispatchDetailMapper.getKcDetailsList(dispatchCode);
        return Message.success(kcDetailsList);
    }
}
