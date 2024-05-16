package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcMedicineMapper;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.service.KcMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Message getKcMedicineByReportedCode(int storehouseId, String reportedCode) {
        List<KcMedicine> list = kcMedicineMapper.getKcMedicineByReportedCode(storehouseId, reportedCode);
        if (list.size() > 0){
            return Message.success(list);
        }
        return Message.error();
    }

    @Override
    public Message updateQuantityById(KcMedicine kcMedicine) {
        int newQuantity = kcMedicine.getQuantity()-kcMedicine.getReportedNum();
        kcMedicine.setQuantity(newQuantity);
        BigDecimal newQuantityBigDecimal = new BigDecimal(newQuantity);
        BigDecimal newMoney = newQuantityBigDecimal.multiply(kcMedicine.getMoney());
        kcMedicine.setMoney(newMoney);
        int isUpdate = kcMedicineMapper.updateQuantityById(kcMedicine);
        if (isUpdate > 0){
            return Message.success();
        }
        return Message.error();
    }
}