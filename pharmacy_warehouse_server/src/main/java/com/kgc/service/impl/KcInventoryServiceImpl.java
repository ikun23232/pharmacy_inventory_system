package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcInventorydetailMapper;
import com.kgc.entity.*;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.service.IKcInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.service.IKcInventorydetailService;
import com.kgc.service.IKcInventoryrkService;
import com.kgc.utils.ExeclUtil;
import com.kgc.utils.TimeUtil;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.PandianDetailVo;
import com.kgc.vo.PandianMedicineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
@Transactional
public class KcInventoryServiceImpl extends ServiceImpl<KcInventoryMapper, KcInventory> implements IKcInventoryService {
    @Autowired
    private KcInventoryMapper kcInventoryMapper;
    @Autowired
    private KcInventorydetailMapper kcInventorydetailMapper;
    @Autowired
    private IKcInventorydetailService iKcInventorydetailService;
    @Autowired
    private IKcInventoryrkService inventoryrkService;

    @Override
    public Message getAllCheckByPage(KcInventoryVo vo, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<KcInventory> allCheckByPage = kcInventoryMapper.getAllCheckByPage(vo);
        PageInfo<KcInventory> pageInfo = new PageInfo<>(allCheckByPage);
        return Message.success(pageInfo);
    }



    @Override
    public Message getKcMedicineByMedicine(KcInventoryVo inventory) {
        List<KcInventorydetail> kcMedicineByMedicine = kcInventoryMapper.getKcMedicineByMedicine(inventory.getWarehouseId(), inventory.getMaterialCategoryId());

        return Message.success(kcMedicineByMedicine);
    }

    @Override
    public Message addCheck(KcInventoryVo inventory) {
        SysUser loginUser = (SysUser) StpUtil.getSession().get("user");
        inventory.setCreateBy(loginUser.getUserid());
        inventory.setBillDate(new Date());
        inventory.setCreateTime(new Date());
        this.save(inventory);
        List<KcMedicine> kcMedicineList = inventory.getKcMedicineList();

        for (KcMedicine kcMedicine : kcMedicineList) {
            KcInventorydetail iKcInventorydetail = new KcInventorydetail();

            iKcInventorydetail.setInventoryId(inventory.getId());
            iKcInventorydetail.setStorehouseId(kcMedicine.getStorehouseId());
            iKcInventorydetail.setMedicineId(kcMedicine.getMedicineId());
            iKcInventorydetail.setProviderId(kcMedicine.getProviderId());
            iKcInventorydetailService.save(iKcInventorydetail);
        }
        return Message.success();
    }

    @Override
    public Message getKcInventoryVoById(Integer id) {
        KcInventoryVo kcInventoryVo = kcInventoryMapper.getKcInventoryVoById(id);
        List<KcInventorydetail> kcInventoryDetailById = kcInventorydetailMapper.getKcInventoryDetailById(id);
        kcInventoryVo.setKcInventorydetailList(kcInventoryDetailById);

        return Message.success(kcInventoryVo);
    }

    @Override
    public Message updateorderStatus(Integer id) {
        boolean b = kcInventoryMapper.updateorderStatus(id);
        if (b) {
            return Message.success(b);
        }
        return Message.error();
    }

    @Override
    public Message updateisVoid(Integer id) {
        boolean b = kcInventoryMapper.updateisVoid(id);
        if (b) {
            return Message.success(b);
        }
        return Message.error();
    }

    @Override
    public Message approveCheck(KcInventoryVo inventory ) {

        if (inventory.getIsApproved()==2){
            List<KcInventorydetail> kcInventorydetailList = inventory.getKcInventorydetailList();
            for (KcInventorydetail kcInventorydetail : kcInventorydetailList){
                List<KcInventorydetail> kcMedicineByMedicine = kcInventoryMapper.getKcMedicineByMedicine(kcInventorydetail.getStorehouseId(), kcInventorydetail.getMedicineId());
                for (KcInventorydetail kcMedicine : kcMedicineByMedicine){
                    //盘亏处理
                    if (kcMedicine.getTotalQuantity()>kcInventorydetail.getExactCount()){




                    }else if (kcMedicine.getTotalQuantity()<kcInventorydetail.getExactCount()){
                        KcInventoryrk kcInventoryrk=new KcInventoryrk();
                        kcInventoryrk.setInventoryCode(TimeUtil.getCurrentTime("PDRK"));
                        kcInventoryrk.setInventorydetailId(inventory.getId());
                        kcInventoryrk.setMedicineId(kcMedicine.getMedicineId());
                        kcInventoryrk.setRkQuantity(kcInventorydetail.getExactCount()-kcMedicine.getTotalQuantity());
                        kcInventoryrk.setStorehouseId(kcInventorydetail.getStorehouseId());
                        kcInventoryrk.setProviderId(kcInventorydetail.getProviderId());
                        inventoryrkService.save(kcInventoryrk);

                    }else {

                    }
                }
            }
            KcInventory vo = new KcInventory();
            vo.setIsApproved(inventory.getIsApproved());
            vo.setApproverRemark(inventory.getApproverRemark());
            vo.setApproverBy(1);
            vo.setOrderStatus(4);
            kcInventoryMapper.update(vo, new UpdateWrapper<KcInventory>()
                    .eq("id", inventory.getId())
            );
            return Message.success("");
        }else {
            KcInventory vo = new KcInventory();
            vo.setIsApproved(inventory.getIsApproved());
            vo.setApproverRemark(inventory.getApproverRemark());
            vo.setApproverBy(1);
            vo.setOrderStatus(3);
            kcInventoryMapper.update(vo, new UpdateWrapper<KcInventory>()
                    .eq("id", inventory.getId())
            );
            return Message.success("审核信息未通过");
        }

    }

    @Override
    public void checkExcel(KcInventoryVo inventoryVo, HttpServletResponse response) {
        List<PandianDetailVo> allCheck = kcInventoryMapper.getAllCheck();
        List<PandianDetailVo> temp = new ArrayList<>();
        for (PandianDetailVo vo : allCheck) {
            List<PandianMedicineVo> allKcInventoryDetailById = kcInventorydetailMapper.getAllKcInventoryDetailById(vo.getId());
            vo.setMedicineList(allKcInventoryDetailById);
            temp.add(vo);
        }
        try {
            ExeclUtil.write(temp, PandianDetailVo.class,response,"盘点明细");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
