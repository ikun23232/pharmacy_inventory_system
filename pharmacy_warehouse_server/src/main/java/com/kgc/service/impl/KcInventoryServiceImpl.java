package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.dao.KcInventorydetailMapper;
import com.kgc.entity.*;
import com.kgc.service.IKcInventoryService;
import com.kgc.service.IKcInventorydetailService;
import com.kgc.service.IKcInventoryrkService;
import com.kgc.service.KcReportedService;
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
import java.util.*;

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
    @Autowired
    private KcReportedService kcReportedService;

    @Override
    public Message getAllCheckByPage(KcInventoryVo vo, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<KcInventory> allCheckByPage = kcInventoryMapper.getAllCheckByPage(vo);
        PageInfo<KcInventory> pageInfo = new PageInfo<>(allCheckByPage);
        return Message.success(pageInfo);
    }


    @Override
    public Message getKcMedicineByMedicine(KcInventoryVo inventory,Page page) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<KcInventorydetail> kcMedicineByMedicine = kcInventoryMapper.getKcMedicineByMedicine(inventory.getWarehouseId(), inventory.getMaterialCategoryId());
        PageInfo pageInfo = new PageInfo(kcMedicineByMedicine);
        return Message.success(pageInfo);
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
    public Message approveCheck(KcInventoryVo inventory) {

        if (inventory.getIsApproved() == 2) {
            List<KcInventorydetail> kcInventorydetailList = inventory.getKcInventorydetailList();
            for (KcInventorydetail kcInventorydetail : kcInventorydetailList) {
                List<KcInventorydetail> kcMedicineByMedicine = kcInventoryMapper.getKcMedicineByMedicine(kcInventorydetail.getStorehouseId(), kcInventorydetail.getMedicineId());
                for (KcInventorydetail kcMedicine : kcMedicineByMedicine) {
                    //盘亏处理
                    if (kcMedicine.getTotalQuantity() > kcInventorydetail.getExactCount()) {

                        Map<String, Object> map = new HashMap<>();// 创建外部Map

                        Map<String, Object> theData = new HashMap<>();// 创建内部Map，包含theData所需的键值对

                        // 设置storehouseId
                        theData.put("storehouseId", kcInventorydetail.getStorehouseId()); // 假设仓库ID为1
                        // 设置documenterBy
                        theData.put("documenterBy", 1); // 假设记录人ID为2
                        // 创建药品列表
                        List<Map<String, Object>> list = new ArrayList<>();
                        // 添加药品信息到列表
                        Map<String, Object> medicineInfo1 = new HashMap<>();
                        medicineInfo1.put("reportedNum", kcMedicine.getTotalQuantity() - kcInventorydetail.getExactCount()); // 假设报告数量为10
                        medicineInfo1.put("medicineId", kcInventorydetail.getMedicineId()); // 假设药品ID为101
                        list.add(medicineInfo1);
                        // 将药品列表添加到theData Map中
                        theData.put("list", list);
                        // 将theData Map添加到外部Map中
                        map.put("theData", theData);
                        kcReportedService.addKcReportedAllByPk(map);
                    } else if (kcMedicine.getTotalQuantity() < kcInventorydetail.getExactCount()) {
                        KcInventoryrk kcInventoryrk = new KcInventoryrk();
                        kcInventoryrk.setInventoryCode(TimeUtil.getCurrentTime("PDRK"));
                        kcInventoryrk.setInventorydetailId(inventory.getId());
                        kcInventoryrk.setMedicineId(kcMedicine.getMedicineId());
                        kcInventoryrk.setRkQuantity(kcInventorydetail.getExactCount() - kcMedicine.getTotalQuantity());
                        kcInventoryrk.setStorehouseId(kcInventorydetail.getStorehouseId());
                        kcInventoryrk.setProviderId(kcInventorydetail.getProviderId());
                        inventoryrkService.save(kcInventoryrk);

                    } else {

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
        } else {
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
            ExeclUtil.write(temp, PandianDetailVo.class, response, "盘点明细");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
