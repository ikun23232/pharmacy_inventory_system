package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.*;
import com.kgc.vo.KcReportedVO;
import com.kgc.vo.KcReportedfromwareVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KcReportedMapper extends BaseMapper<KcReported> {
    /**
     * 获取报损库存列表
     * @param kcReported
     * @return
     */
    List<KcReported> getKcReportedList(KcReported kcReported);

    /**
     * 根据id获取报损单
     * @param id
     * @return
     */
    KcReported getKcReportedListById(@Param("id") Integer id);

    /**
     * 根据code获取报损单号
     * @param code
     * @return
     */
    List<String> getCodeByCode(String code);

    /**
     * 获取仓库列表
     * @return
     */
    List<BaseStorehouse> getStorehouseList();

    /**
     * 获取报损类型
     * @return
     */
    List<KcReportedtype> getReportedType();

    /**
     * 添加报损单
     * @param kcReported
     * @return
     */
    int addKcReported(KcReported kcReported);

    int addKcReporteddetail(KcReporteddetail kcReporteddetail);

    KcReported getKcReportedByCode(String code);

    int updateReportedByCode(KcReported kcReported);

    int delKcReporteddetailByCode(@Param("reportedCode") String reportedCode);

    int delKcReportedByCode(@Param("code") String code);

    int addKcReportedfromwareByReported(KcReportedfromware kcReportedfromware);

    List<KcReportedfromware> getKcReportedfromware(KcReportedfromware kcReportedfromware);

    List<KcReportedfromwareVO> getKcReportedfromwareVO();

    List<KcReportedVO> getKcReportedVOList(KcReportedVO kcReportedVO);


}
