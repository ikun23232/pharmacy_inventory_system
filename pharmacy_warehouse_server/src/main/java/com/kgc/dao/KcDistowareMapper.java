package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDistoware;

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
public interface KcDistowareMapper extends BaseMapper<KcDistoware> {
    List<KcDistoware> getKcDistowareList(Map map);

    KcDistoware getKcDistowareOrder(int id);

    List<KcDistoware> getKcDistowareListByExcel(Map map);
}
