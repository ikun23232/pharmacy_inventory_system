package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDisfromware;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface KcDisfromwareMapper extends BaseMapper<KcDisfromware> {

    List<KcDisfromware> getKcDisfromwareList(Map map);

    KcDisfromware getKcDisfromwareOrder(int id);
}
