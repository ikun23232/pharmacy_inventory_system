package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CgddOrder;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 14:41
 */
public interface ProcurementOrderMapper extends BaseMapper<CgddOrder> {
    public List<CgddOrder> getCgddOrder(CgddOrder cgddOrder);
}
