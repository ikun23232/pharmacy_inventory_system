package com.kgc.dao;

import com.kgc.entity.KcInventory;
import com.kgc.entity.KcInventoryrk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcMedicine;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.PndianRKVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-05-25
 */
@Mapper
public interface KcInventoryrkMapper extends BaseMapper<KcInventoryrk> {
    List<KcInventoryrk> getAllCheckRkByPage(KcInventoryrk vo);
    List<PndianRKVo> getAllCheckRkexcel(Integer id);

    KcMedicine getMeddicinequantity(KcInventoryrk vo);

    KcInventoryrk getMeddicinequantityById(Integer id);

}
