package com.kgc.dao;

import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.KcReported;
import com.kgc.entity.KcReportedtype;

import java.util.List;

public interface KcReportedMapper {
    /**
     * 获取报损库存列表
     * @param kcReported
     * @return
     */
    List<KcReported> getKcReportedList(KcReported kcReported);

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

}
