package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcSalefromware;
import com.kgc.vo.KcSalefromwareVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SaleOutWarehouseMapper extends BaseMapper<KcSalefromware> {

    public List<KcSalefromware> getSaleOutWarehouseListByPage(KcSalefromware kcSalefromware);

    public List<KcSalefromwareVo> getSaleOutWarehouseList(KcSalefromware kcSalefromware);
}
