package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcReportedMapper;
import com.kgc.entity.*;
import com.kgc.service.KcReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class KcReportedServiceImpl implements KcReportedService {

    @Autowired
    private KcReportedMapper kcReportedMapper;

    /**
     * 分页获取库存报损列表
     * @param kcReported
     * @param pageNum
     * @param pageSize
     */
    @Override
    public Message getKcReportedList(KcReported kcReported,int pageNum,int pageSize) {
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (kcReported.getBeginTime()!=null&& " ".equals(kcReported.getBeginTime())){
                Date beginDate = inputSdf.parse(kcReported.getBeginTime());
                Date endDate = inputSdf.parse(kcReported.getEndTime());
                kcReported.setBeginTime(outputSdf.format(beginDate));
                kcReported.setEndTime(outputSdf.format(endDate));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<KcReported> kcReportedList = kcReportedMapper.getKcReportedList(kcReported);
        PageInfo<KcReported> kcReportedPageInfo = new PageInfo<>(kcReportedList);
        if (!kcReportedList.isEmpty()){
            return Message.success(kcReportedPageInfo);
        }
        return Message.error();
    }
    /**
     * 根据code获取code
     * @param code
     */
    @Override
    public Message getCodeByCode(String code) {
        List<String> codeByCode = kcReportedMapper.getCodeByCode(code);
        if (!codeByCode.isEmpty()){
            return Message.success(codeByCode);
        }
        return Message.error();
    }
    /**
     * 获取仓库列表
     */
    @Override
    public Message getStorehouseList() {
        List<BaseStorehouse> storehouseList = kcReportedMapper.getStorehouseList();
        if (!storehouseList.isEmpty()){
            return Message.success(storehouseList);
        }
        return Message.error();
    }
    /**
     * 获取报损类型
     */
    @Override
    public Message getReportedType() {
        List<KcReportedtype> reportedType = kcReportedMapper.getReportedType();
        if (!reportedType.isEmpty()){
            return Message.success(reportedType);
        }
        return Message.error();
    }
    /**
     * 添加库存报损
     * @param kcReported
     */
    @Override
    public Message addKcReported(KcReported kcReported) {
        int isAdd = kcReportedMapper.addKcReported(kcReported);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 添加库存报损明细
     * @param kcReporteddetail
     */
    @Override
    public Message addKcReporteddetail(KcReporteddetail kcReporteddetail) {
        int isAdd = kcReportedMapper.addKcReporteddetail(kcReporteddetail);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }
    /**
     * 修改库存报损
     * @param kcReported
     */
    @Override
    public Message updateReportedByCode(KcReported kcReported) {
        int isUpdate = kcReportedMapper.updateReportedByCode(kcReported);
        if (isUpdate>0){
            return Message.success();
        }
        return Message.error();
    }

    @Override
    public Message getKcReportedByCode(String code) {
        return Message.success(kcReportedMapper.getKcReportedByCode(code));
    }

    @Override
    public Message delKcReporteddetailByCode(String reportedCode) {
        int isDel = kcReportedMapper.delKcReporteddetailByCode(reportedCode);
        if (isDel>0){
            return Message.success();
        }
        return Message.error();
    }

    @Override
    public Message delKcReportedByCode(String code) {
        int isDel = kcReportedMapper.delKcReportedByCode(code);
        if (isDel>0){
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 删除库存报损
     * @param code
     * @return
     */
    @Override
    public Message delKcReportedAndDetailByCode(String code) {
        KcReported kcReported = (KcReported) getKcReportedByCode(code).getData();
        if (kcReported.getApprovalStatus()==1){
            return Message.error("该报损单已审批，不能删除");
        }
        Message isDelReported = delKcReportedByCode(code);
        Message isDelDetail = delKcReporteddetailByCode(code);
        if (isDelReported.getCode().equals("200")&&isDelDetail.getCode().equals("200")){
            return Message.success();
        }
        return Message.error();
    }


}
