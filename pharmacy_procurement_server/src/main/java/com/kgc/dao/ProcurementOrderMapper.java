package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CgPayCom;
import com.kgc.entity.CgPayNum;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.vo.CgddVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 14:41
 */
@Mapper
public interface ProcurementOrderMapper extends BaseMapper<CgddOrder> {
    public List<CgddOrder> getCgddOrder(CgddOrder cgddOrder);

    List<CgPayCom> getCgPayCom(@Param("year") String year, @Param("month") String month);

    List<CgPayNum> getCgPayNum(@Param("year") String year);

    public CgddOrder getCgddByCode(CgddOrder cgddOrder);

    BigDecimal getReferenceAmountByCode(@Param("code") String code);

    int addcgyf(CwCgyf cwCgyf);

    int updateCgddIsPayByCode(@Param("code") String code);
    public List<CgddVO> imExcel();
}
