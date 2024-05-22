package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.*;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.service.IKcInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.service.IKcInventorydetailService;
import com.kgc.vo.KcInventoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 盘点单 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-05-20
 */
@Service
public class KcInventoryServiceImpl extends ServiceImpl<KcInventoryMapper, KcInventory> implements IKcInventoryService {
    @Autowired
    private KcInventoryMapper kcInventoryMapper;
    @Autowired
    private IKcInventorydetailService iKcInventorydetailService;

    @Override
    public Message getAllCheckByPage(KcInventoryVo vo, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<KcInventory> allCheckByPage = kcInventoryMapper.getAllCheckByPage(vo);
        PageInfo<KcInventory> pageInfo = new PageInfo<>(allCheckByPage);
        return Message.success(pageInfo);
    }



    @Override
    public Message getKcMedicineByMedicine(KcInventoryVo inventory) {
        List<KcMedicine> kcMedicineByMedicine = kcInventoryMapper.getKcMedicineByMedicine(inventory.getWarehouseId(), inventory.getMaterialCategoryId());

        return Message.success(kcMedicineByMedicine);
    }

    @Override
    public Message addCheck(KcInventoryVo inventory) {
        inventory.setBillDate(new Date());
        this.save(inventory);
        List<KcMedicine> kcMedicineList = inventory.getKcMedicineList();
        KcInventorydetail iKcInventorydetail=new  KcInventorydetail();
        for (KcMedicine kcMedicine : kcMedicineList){
            iKcInventorydetail.setInventoryId(inventory.getId());
            iKcInventorydetail.setStorehouseId(kcMedicine.getStorehouseId());
            iKcInventorydetail.setMedicineId(kcMedicine.getMedicineId());
            iKcInventorydetail.setProviderId(kcMedicine.getProviderId());
            iKcInventorydetailService.save(iKcInventorydetail);
        }
        return Message.success();
    }
}
