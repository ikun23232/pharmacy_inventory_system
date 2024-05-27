package com.kgc.service.impl;

import com.kgc.entity.KcType;
import com.kgc.dao.KcTypeMapper;
import com.kgc.entity.Message;
import com.kgc.service.KcTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class KcTypeServiceImpl extends ServiceImpl<KcTypeMapper, KcType> implements KcTypeService {
    @Autowired
    private KcTypeMapper kcTypeMapper;

    @Override
    public Message getKcTypeList() {
        List<KcType> kcTypes = kcTypeMapper.selectList(null);
        return Message.success(kcTypes);
    }
}
