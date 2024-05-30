package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDistoware;
import com.kgc.vo.DispatchToWareVO;

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
    List<DispatchToWareVO> getKcDistowareList(Map map);

    DispatchToWareVO getKcDistowareOrder(int id);

    List<KcDistoware> getKcDistowareListByExcel(Map map);
}
