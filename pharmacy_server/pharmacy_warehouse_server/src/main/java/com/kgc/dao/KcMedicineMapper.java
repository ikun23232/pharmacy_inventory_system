package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KcMedicineMapper extends BaseMapper<KcMedicine> {

    //查询库存药品
    List<KcMedicine> getKcMedicine(KcMedicine kcMedicine);

    List<KcMedicine> getKcMedicineByReportedCode(@Param("storehouseId") int storehouseId,@Param("reportedCode") String reportedCode);

    int updateQuantityById(KcMedicine kcMedicine);

    KcMedicine getMedicineByBatchCode(@Param("batchCode") String batchCode,@Param("storehouseId") Integer storehouseId);

    /**
     * 查询低于告警值的本地仓库详情
     * @return
     */
    List<KcMedicine> getWarningByLocalHouse();

    List<BaseMedicine> getWarningByTotalCount();
}
