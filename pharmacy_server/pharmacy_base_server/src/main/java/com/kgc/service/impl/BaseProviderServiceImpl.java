package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseProviderMapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import com.kgc.service.BaseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class BaseProviderServiceImpl extends ServiceImpl<BaseProviderMapper, BaseProvider> implements BaseProviderService {

    @Autowired
    private BaseProviderMapper baseProviderMapper;
    @Override
    public Message getBaseProviderList(String name,int type,int currentPageNo,int pageSize) {
        PageHelper.startPage(currentPageNo,pageSize);
        List<BaseProvider> baseProviderList = baseProviderMapper.getBaseProviderList(name,type);
        PageInfo<BaseProvider> page = new PageInfo<>(baseProviderList);
        page.setList(baseProviderList);
        return Message.success(page);
    }

    @Override
    public Message delBaseProvider(int id) {
        int del = baseProviderMapper.del(id);
        if(del > 0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message addBaseProvider(BaseProvider baseProvider) {
        baseProvider.setCreateDate(new Date());
        int insert = baseProviderMapper.insert(baseProvider);
        if(insert>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message updateBaseProvider(BaseProvider baseProvider) {
        BaseProvider baseProvider1 = baseProviderMapper.getBaseProviderById(baseProvider.getId());
        baseProvider.setUpdateDate(new Date());
        baseProvider.setCreateDate(baseProvider1.getCreateDate());
        UpdateWrapper<BaseProvider> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",baseProvider.getId());
        int update = baseProviderMapper.update(baseProvider,wrapper);
        if(update>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message getBaseProviderById(int id) {
        BaseProvider baseProviderById = baseProviderMapper.getBaseProviderById(id);
        if(baseProviderById!=null){
            return Message.success(baseProviderById);
        }else {
            return Message.error();
        }

    }

    @Override
    public Message checkaddBaseProvider(String name) {
        BaseProvider baseProvider = baseProviderMapper.checkaddBaseProvider(name);
        if(baseProvider!=null){
            return Message.error();
        }else {
            return Message.success();
        }
    }

    @Override
    public Message checkupdateBaseProvider(String name,int id) {
        BaseProvider baseProvider = baseProviderMapper.checkaddBaseProvider(name);
        BaseProvider baseProvider1 = baseProviderMapper.getBaseProviderById(id);
        if(baseProvider!=null){
            if(baseProvider.getName().equals(baseProvider1.getName())){
                return Message.success();
            }
            return Message.error();
        }else {
            return Message.success();
        }
    }
}
