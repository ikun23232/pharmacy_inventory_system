package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseProviderMapper;
import com.kgc.dao.BaseProviderTypeMapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.BaseProviderType;
import com.kgc.entity.Message;
import com.kgc.service.BaseProviderService;
import com.kgc.service.BaseProviderTypeService;
import org.springframework.stereotype.Service;

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
public class BaseProviderTypeServiceImpl extends ServiceImpl<BaseProviderTypeMapper, BaseProviderType> implements BaseProviderTypeService {

}
