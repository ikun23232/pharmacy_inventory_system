package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcOutintodetial;
import com.kgc.entity.KcOutintodetial;
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
public interface KcOutintodetialMapper extends BaseMapper<KcOutintodetial> {

    List<KcOutintodetial> getKcDisfromwareList(Map paramsMap);
}
