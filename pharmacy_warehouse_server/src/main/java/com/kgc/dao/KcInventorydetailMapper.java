package com.kgc.dao;

import com.kgc.entity.KcInventorydetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcMedicine;
import com.kgc.vo.PandianMedicineVo;
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
    List<KcInventorydetail> getKcMedicineByMedicine(@Param("storehouseId") Integer storehouseId, @Param("materialCategoryId") Integer materialCategoryId);

    List<KcInventorydetail> getKcInventoryDetailById(Integer id);
    List<KcInventorydetail> getKcInventoryDetailByIdwithother(Integer id);

    String getKcInventoryUnitName(@Param("warehouseId") Integer warehouseId,@Param("medecineId")Integer medecineId);
    boolean deleteKcInventory(Integer id);
    KcInventorydetail getKcInventoryByMeandwaandpro(@Param("warehouseId") Integer warehouseId,@Param("medecineId")Integer medecineId,@Param("providerId")Integer providerId,@Param("id")Integer id);


    List<PandianMedicineVo> getAllKcInventoryDetailById(Integer id);
}
