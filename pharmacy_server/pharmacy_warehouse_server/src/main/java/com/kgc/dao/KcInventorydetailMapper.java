package com.kgc.dao;

import com.kgc.entity.KcInventorydetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcMedicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-05-21
 */
@Mapper
public interface KcInventorydetailMapper extends BaseMapper<KcInventorydetail> {
    List<KcInventorydetail> getKcMedicineByMedicine(@Param("storehouseId") Integer storehouseId, @Param("medicineId") Integer medicineId);
}
