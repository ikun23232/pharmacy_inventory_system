package com.kgc.dao;

import com.kgc.entity.KcInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcInventorydetail;
import com.kgc.entity.KcMedicine;
import com.kgc.vo.KcInventoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 盘点单 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-05-20
 */
@Mapper
public interface KcInventoryMapper extends BaseMapper<KcInventory> {
    List<KcInventory> getAllCheckByPage(KcInventoryVo vo);

    List<KcMedicine> getKcMedicineByMedicine(@Param("storehouseId") Integer storehouseId, @Param("materialCategoryId") Integer materialCategoryId);

}
