package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.service.KcMedicineService;
import com.kgc.vo.KcMedicineBSVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class KcMedicineServiceImpl implements KcMedicineService {

    @Autowired
    private KcMedicineMapper kcMedicineMapper;

    @Override
    public Message getKcMedicine(KcMedicine kcMedicine, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<KcMedicine> list = kcMedicineMapper.getKcMedicine(kcMedicine);
        PageInfo<KcMedicine> pageInfo = new PageInfo<>(list);
        if (list.size() > 0){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message getKcMedicineByReportedCode(String reportedCode) {
        List<KcMedicine> list = kcMedicineMapper.getKcMedicineByReportedCode(reportedCode);
        if (list.size() > 0){
            return Message.success(list);
        }
        return Message.error();
    }

    @Override
    public List<KcMedicine> getKcMedicineByReportedCodeTo(String reportedCode) {
        List<KcMedicine> list = kcMedicineMapper.getKcMedicineByReportedCode(reportedCode);
        if (list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public Message updateQuantityById(KcMedicine kcMedicine) {
        kcMedicine.setQuantity(kcMedicine.getQuantity() - kcMedicine.getReportedNum());
        int isUpdate = kcMedicineMapper.updateQuantityById(kcMedicine);
        if (isUpdate > 0){
            return Message.success();
        }
        return Message.error();
    }

    @Override
    public List<KcMedicineBSVO> getKcMedicineVOByReportedCode(String reportedCode) {
        return kcMedicineMapper.getKcMedicineVOByReportedCode(reportedCode);
    }

    @Override
    public List<KcMedicine> getMedicineByStorehouseIdMedicineId(KcMedicine kcMedicine) {
        return kcMedicineMapper.getMedicineByStorehouseIdMedicineId(kcMedicine);
    }

    @Override
    public Message getKcMedicineByWareHouseId(BaseMedicine baseMedicine) {
        PageHelper.startPage(baseMedicine.getCurrentPage(),5);
        List<BaseMedicine> kcMedicineByWareHouseId = kcMedicineMapper.getKcMedicineByWareHouseId(baseMedicine.getStoreHouseId());
        PageInfo<BaseMedicine> pageInfo=new PageInfo<>(kcMedicineByWareHouseId);
        if(pageInfo!=null) {
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }
}
