package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.KcMedicine;
import com.kgc.vo.MedicineVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseMedicineMapper extends BaseMapper<BaseMedicine> {


    /**
     * 分页查询药品
     *
     * @return
     */
    public List<BaseMedicine> getBaseMedicineListByPage(BaseMedicine baseMedicine);

    List<BaseMedicine> getMedicineListByCode(String code);

    List<MedicineVO> getMedicineVOListByCode(String code);

    List<BaseMedicine> getBaseMedicineListByProviderId(int providerId);

    public BaseMedicine getBaseMedicineById(@Param("id") int id,@Param("batchCode")int batchCode);

    /**
     * 查询本地仓库的所有药品信息
    List<BaseMedicine> getMedicineListByCode(@Param("code") String code);

    public BaseMedicine getBaseMedicineById(@Param("id") int id, @Param("batchCode") int batchCode);

    /**
     * 查询本地仓库的所有药品信息
     *
     * @return
     */
    public List<BaseMedicine> getAllBaseMedicine();

    public List<BaseMedicine> getAllBaseMedicineList();

    /**
     * 根据药品Id查询本地仓库的所有批次号
     *
     * @param medicineId
     * @return
     */
    public List<KcMedicine> getAllBatchCodeByMedicineId(@Param("medicineId") int medicineId);

    List<BaseMedicine> findProductsByCategoryId(Integer categoryId);
}