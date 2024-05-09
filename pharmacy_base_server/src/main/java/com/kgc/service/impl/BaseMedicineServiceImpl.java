package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class BaseMedicineServiceImpl extends ServiceImpl<BaseMedicineMapper, BaseMedicine> implements BaseMedicineService {

    @Autowired
    private BaseMedicineMapper baseMedicineMapper;

    @Override
    public Message getBaseMedicineList(int currentPage) {
        Message message=new Message();
        PageHelper.startPage(currentPage,5);
        List<BaseMedicine> baseMedicineList=baseMedicineMapper.getBaseMedicineList();
        PageInfo<BaseMedicine> pageInfo=new PageInfo<>(baseMedicineList);
        if(pageInfo!=null){
            message.setCode("200");
            message.setData(pageInfo);
        }
        return message;
    }

    public Message addBaseMedicine(BaseMedicine baseMedicine){
        Message message=new Message();
        int count=baseMedicineMapper.insert(baseMedicine);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message updateBaseMedicineById(BaseMedicine baseMedicine) {
        Message message=new Message();
        int count=baseMedicineMapper.updateById(baseMedicine);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message deleteBaseMedicineById(int id) {
        Message message=new Message();
        int count=baseMedicineMapper.deleteById(id);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }
}
