package com.kgc.service.impl;

import ch.qos.logback.core.db.dialect.MsSQLDialect;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseMedicineCategoryMapper;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.BaseMedicineCategory;
import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class BaseMedicineServiceImpl extends ServiceImpl<BaseMedicineMapper, BaseMedicine> implements BaseMedicineService {

    @Autowired
    private BaseMedicineMapper baseMedicineMapper;
    @Autowired
    private BaseMedicineCategoryMapper baseMedicineCategoryMapper;


    @Override
    public Message getBaseMedicineListByPage(BaseMedicine baseMedicine) {
        Message message=new Message();
        int currentPage= baseMedicine.getCurrentPage();
        PageHelper.startPage(currentPage,5);
        List<BaseMedicine> baseMedicineList=baseMedicineMapper.getBaseMedicineListByPage(baseMedicine);
        PageInfo<BaseMedicine> pageInfo=new PageInfo<>(baseMedicineList);
        if(pageInfo!=null){
            message.setCode("200");
            message.setData(pageInfo);
        }
        return message;
    }

    public Message addBaseMedicine(BaseMedicine baseMedicine){
        Message message=new Message();
        int count=baseMedicineMapper.insert(baseMedicine);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message getBaseMedicineById(int id,int batchCode) {
        Message message=new Message();
        BaseMedicine baseMedicine=baseMedicineMapper.getBaseMedicineById(id,batchCode);
        if(baseMedicine!=null){
            message.setCode("200");
            message.setData(baseMedicine);
        }
        return message;
    }

    @Override
    public Message updateBaseMedicineById(BaseMedicine baseMedicine) {
        Message message=new Message();
        int count=baseMedicineMapper.updateById(baseMedicine);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message deleteBaseMedicineById(int id) {
        Message message=new Message();
        int count=baseMedicineMapper.deleteById(id);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message getMedicineListByCode(String code) {
        List<BaseMedicine> medicineListByCode = baseMedicineMapper.getMedicineListByCode(code);
        return Message.success(medicineListByCode);
    }

    @Override
    public Message getAllBaseMedicine() {
        List<BaseMedicine> baseMedicineList=baseMedicineMapper.getAllBaseMedicine();
        if(baseMedicineList!=null){
            return Message.success(baseMedicineList);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getAllBatchCodeByMedicineId(int medicineId) {
        List<KcMedicine> kcMedicineList=baseMedicineMapper.getAllBatchCodeByMedicineId(medicineId);
        if(kcMedicineList!=null){
            return Message.success(kcMedicineList);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getTreeMedicine() {

        List<BaseMedicineCategory> categories = baseMedicineCategoryMapper.getAllCategoryList();
        for (BaseMedicineCategory category : categories) {
            category.setChildren(getChildrenCategoriesWithProducts(category.getId()));
        }
        return Message.success(categories);
    }

    private List<BaseMedicineCategory> getChildrenCategoriesWithProducts(Integer parentId) {
        List<BaseMedicineCategory> children = baseMedicineCategoryMapper.findChildrenCategoriesByParentId(parentId);
        for (BaseMedicineCategory child : children) {
            child.setChildren(getChildrenCategoriesWithProducts(child.getId()));
            child.setMedicine(baseMedicineMapper.findProductsByCategoryId(child.getId()));
        }
        return children;
    }
}