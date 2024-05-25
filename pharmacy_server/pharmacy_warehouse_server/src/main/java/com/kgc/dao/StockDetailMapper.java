package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.KcSalefromware;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockDetailMapper extends BaseMapper<BaseMedicine> {

    public List<BaseMedicine> getStockDetailListByPage(BaseMedicine baseMedicine);

    public List<BaseMedicine> getStockDetailListByPage();

    public int reduceStockDetailByMedicineId(@Param("medicineId") int medicineId,@Param("quantity") int quantity);
}
