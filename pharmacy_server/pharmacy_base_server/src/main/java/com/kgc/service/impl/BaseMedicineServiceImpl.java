package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

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
        logger.debug("getMedicineListByCode code:"+code);
        List<BaseMedicine> medicineListByCode = baseMedicineMapper.getMedicineListByCode(code);
        return Message.success(medicineListByCode);
    }

    @Override
    public Message getBaseMedicineListByProviderId(int providerId) {
        List<BaseMedicine> baseMedicineList=baseMedicineMapper.getBaseMedicineListByProviderId(providerId);
        return Message.success(baseMedicineList);
    }

    @Override
    public Message getMedicineListByCodeComblie(String code) {
        List<BaseMedicine> medicineListByCode = baseMedicineMapper.getMedicineListByCode(code);
        ArrayList<BaseMedicine> baseMedicines = new ArrayList<>();
        if (medicineListByCode!=null){
            boolean falg=true;
            for (BaseMedicine baseMedicine : medicineListByCode) {
               if (baseMedicines!=null&&baseMedicines.size()>0){
                   for (int i = 0; i < baseMedicines.size(); i++) {
                       if (baseMedicines.get(i).getId()==baseMedicine.getId()){
                           baseMedicines.get(i).setQuantity(baseMedicines.get(i).getQuantity()+baseMedicine.getQuantity());
                           System.out.println(baseMedicines.get(i).getQuantity());
                           falg=false;
                           break;
                       }
                   }
               }
               if (falg){
                   baseMedicines.add(baseMedicine);
               }
            }
        }


        return Message.success(baseMedicines);
    }
}
