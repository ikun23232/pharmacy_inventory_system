package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Message getBaseMedicineById(int id) {
        Message message=new Message();
        BaseMedicine baseMedicine=baseMedicineMapper.selectById(id);
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
    public Message getBaseMedicineListByProviderId(int providerId) {
        List<BaseMedicine> baseMedicineList=baseMedicineMapper.getBaseMedicineListByProviderId(providerId);
        return Message.success(baseMedicineList);
    }
}
