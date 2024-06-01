package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.*;
import com.kgc.vo.KcReportedVO;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface KcReportedService extends IService<KcReported> {
    //查询库存报损列表
    Message getKcReportedList(KcReported kcReported,int pageNum,int pageSize);
    //根据id查询库存报损
    Message getKcReportedListById(Integer id);
    //根据code查询code
    Message getCodeByCode(String code);
    //查询仓库列表
    Message getStorehouseList();
    //查询报损类型
    Message getReportedType();
    //添加库存报损
    Message addKcReported(KcReported kcReported);
    //添加库存报损详情
    Message addKcReporteddetail(KcReporteddetail kcReporteddetail);
    //修改库存报损
    Message updateReportedByCode(KcReported kcReported);
    //根据code查询库存报损
    Message getKcReportedByCode(String code);


    //删除库存报损详情
    Message delKcReporteddetailByCode(String reportedCode);
    //删除库存报损
    Message delKcReportedByCode(String code);

    Message noCanReportedByCode(String code);

    //添加库存报损和详情
    Message addKcReportedAndDetail(Map map);
    //修改库存报损和详情
    Message updateReportedAndDetail(Map map);
    //删除库存报损和详情
    Message delKcReportedAndDetailByCode(String code);
    //添加库存报损来源
    Message addKcReportedfromwareByReported(int reportedId);
    //查询报损出库
    Message getKcReportedfromware(KcReportedfromware kcReportedfromware,int pageNum,int pageSize);
    //查询所有库存报损
    List<KcReported> getAllKcReported();
    //库报损excel
    void kcReportedExcel(KcReportedVO kcReportedVO, HttpServletResponse response);
    //库报损入库excel
    void kcReportedfromwareExcel(HttpServletResponse response);

    Message addKcReportedAllByPk(Map map);

    int addKcReportedBits(KcReported kcReported);

    Message insertKcReported(KcReported kcReported);

}
