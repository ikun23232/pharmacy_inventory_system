package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.*;
import com.kgc.entity.*;
import com.kgc.service.KcDispatchService;
import com.kgc.utils.CodeUtil;
import com.kgc.vo.DispatchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:10
 */
@Service
@Transactional
public class KcDispatchServiceImpl extends ServiceImpl<KcDispatchMapper, KcDispatch> implements KcDispatchService {

    @Autowired
    private KcDispatchMapper kcDispatchMapper;
    @Autowired
    private KcDispatchDetailMapper dispatchDetailMapper;
    @Autowired
    private KcDisfromwareMapper kcDisfromwareMapper;
    @Autowired
    private KcDistowareMapper kcDistowareMapper;
    @Autowired
    private KcOutintodetialMapper kcOutintodetialMapper;
    @Autowired
    private KcMedicineMapper kcMedicineMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Message getKcDispathList(DispatchVO dispatchVO) {
//        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date beginDate = inputSdf.parse(dispatchVO.getStartTime());
//            Date endDate = inputSdf.parse(dispatchVO.getEndTime());
//            dispatchVO.setStartTime(outputSdf.format(beginDate));
//            dispatchVO.setEndTime(outputSdf.format(endDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        PageHelper.startPage(dispatchVO.getCurrentPageNo(), dispatchVO.getPageSize());
        List<KcDispatch> kcDispathList = kcDispatchMapper.getKcDispathList(dispatchVO);
        PageInfo pageInfo = new PageInfo(kcDispathList);
        return Message.success(pageInfo);
    }

    @Override
    public Message addKcDispatch(KcDispatch kcDispatch) {
        int count1 = 0;
        int totalCount = 0;
        BigDecimal price  =BigDecimal.ZERO;
        for (BaseMedicine baseMedicine: kcDispatch.getMedicineList()) {
            KcDispatchdetails kcDispatchDetails = new KcDispatchdetails();
            kcDispatchDetails.setBatchCode(baseMedicine.getBatchCode());
            kcDispatchDetails.setMedicineId(baseMedicine.getMedicineId());
            kcDispatchDetails.setAimStoreHouseId(baseMedicine.getAimStoreHouseId());
            kcDispatchDetails.setQuantity(baseMedicine.getQuantity());
            kcDispatchDetails.setPrice(baseMedicine.getPurchasePrice());
            kcDispatchDetails.setDispatchCode(kcDispatch.getCode());
            kcDispatchDetails.setProviderId(baseMedicine.getProviderId());
            int temp = dispatchDetailMapper.insert(kcDispatchDetails);
            if (temp > 0){
                count1++;
                totalCount += baseMedicine.getQuantity();
                price = price.add(kcDispatchDetails.getPrice().multiply(BigDecimal.valueOf(baseMedicine.getQuantity())));
//                QueryWrapper<KcMedicine> query = Wrappers.query();
//                query.eq("batchCode",kcDispatchDetails.getBatchCode());
//                KcMedicine kcMedicine = new KcMedicine();
//                kcMedicine.setQuantity(baseMedicine.getQuantity());
//                kcMedicineMapper.update()
            }
        }
        if (kcDispatch.getMedicineList().size() != count1){
            return Message.error("添加失败");
        }
        kcDispatch.setPrice(price);
        if (kcDispatch.getIsCommit() == 1){
            kcDispatch.setOrderStatus(2);
        }else {
            kcDispatch.setOrderStatus(1);
        }
        kcDispatch.setDocumenterBy(1);
        kcDispatch.setTotalCount(totalCount);
        int count = kcDispatchMapper.insert(kcDispatch);
        if (count > 0){
            return Message.success();
        }
        throw new RuntimeException("添加药品详细失败");
    }

    @Override
    public Message getKcDispatchById(KcDispatch kcDispatch) {
        KcDispatch kcDispatch1 = kcDispatchMapper.selectById(kcDispatch.getId());
        return Message.success(kcDispatch1);
    }

    @Override
    public Message updateDispatchBy(KcDispatch kcDispatch) {
        int count1 = 0;
        int totalCount = 0;
        BigDecimal price  =BigDecimal.ZERO;
        int count2 = dispatchDetailMapper.deleteDispatchByCode(kcDispatch.getCode());
        if (count2 > 0){
            for (BaseMedicine baseMedicine: kcDispatch.getMedicineList()) {
                KcDispatchdetails kcDispatchDetails = new KcDispatchdetails();
                kcDispatchDetails.setBatchCode(baseMedicine.getBatchCode());
                kcDispatchDetails.setMedicineId(baseMedicine.getMedicineId());
                kcDispatchDetails.setAimStoreHouseId(baseMedicine.getAimStoreHouseId());
                kcDispatchDetails.setQuantity(baseMedicine.getQuantity());
                kcDispatchDetails.setPrice(baseMedicine.getPurchasePrice());
                kcDispatchDetails.setDispatchCode(kcDispatch.getCode());
                kcDispatchDetails.setProviderId(baseMedicine.getProviderId());
                int temp = dispatchDetailMapper.insert(kcDispatchDetails);
                if (temp > 0){
                    count1++;
                    totalCount += baseMedicine.getQuantity();
                    price = price.add(kcDispatchDetails.getPrice().multiply(BigDecimal.valueOf(baseMedicine.getQuantity())));
                }
            }
            if (kcDispatch.getMedicineList().size() != count1){
                throw new RuntimeException("添加调度药品详细失败");
            }
            kcDispatch.setPrice(price);
            if (kcDispatch.getIsCommit() == 1){
                kcDispatch.setOrderStatus(2);
            }else {
                kcDispatch.setOrderStatus(1);
            }
            kcDispatch.setUpdateBy(1);
            kcDispatch.setUpdateDate(new Date());
            kcDispatch.setTotalCount(totalCount);
            int count = kcDispatchMapper.updateById(kcDispatch);
            if (count > 0){
                return Message.success();
            }
            throw new RuntimeException("修改调度单失败");
        }
        return Message.error("删除记录失败！");
    }

    @Override
    public Message auditingDispatch(KcDispatch kcDispatch) {
        int addCount = 0;
        if (kcDispatch.getApprovalStatus() == 2){
            String ddrk = CodeUtil.createCode("DDRK");
            KcDisfromware kcDisfromware = new KcDisfromware();
            kcDisfromware.setCode(ddrk);
            kcDisfromware.setDispatchid(kcDispatch.getId());
            int count = kcDisfromwareMapper.insert(kcDisfromware);
            if (count == 0){
                return Message.error("添加调度出库信息失败！");
            }
            String ddck = CodeUtil.createCode("DDCK");
            KcDistoware kcDistoware = new KcDistoware();
            kcDistoware.setCode(ddck);
            kcDistoware.setDispatchid(kcDispatch.getId());
            int count1 = kcDistowareMapper.insert(kcDistoware);
            if (count1 == 0){
                throw new RuntimeException("添加调度入库失败！");
            }
            for(BaseMedicine baseMedicine: kcDispatch.getMedicineList()){
                KcOutintodetial kcOutintodetial = new KcOutintodetial();
                String crkmx = CodeUtil.createCode("CRKMX");
                kcOutintodetial.setCode(crkmx);
                kcOutintodetial.setTypeId(1);
                kcOutintodetial.setCreateBy(1);
                kcOutintodetial.setCreateDate(new Date());
                kcOutintodetial.setOrderCode(kcDispatch.getCode());
                kcOutintodetial.setBatchCode(baseMedicine.getBatchCode());
                kcOutintodetial.setMedicineId(baseMedicine.getMedicineId());
                kcOutintodetial.setPrice(baseMedicine.getPurchasePrice());
                kcOutintodetial.setProviderId(baseMedicine.getProviderId());
                kcOutintodetial.setFromStockMoney(baseMedicine.getPurchasePrice().multiply(BigDecimal.valueOf(baseMedicine.getQuantity())));
                kcOutintodetial.setFromStockQuantity(baseMedicine.getQuantity());
                kcOutintodetial.setWareHouseId(kcDispatch.getBeforeWarehouseId());
                kcOutintodetial.setPrice(baseMedicine.getPurchasePrice());
                logger.info("getKcDispathList auditingDispatch inkcOutintodetial："+kcOutintodetial);
                KcMedicine medicine = kcMedicineMapper.getMedicineByBatchCode(baseMedicine.getBatchCode(),kcDispatch.getBeforeWarehouseId());
                medicine.setQuantity(medicine.getQuantity() - baseMedicine.getQuantity());
                logger.info("getKcDispathList auditingDispatch medicine："+medicine);
                int updateQuantity = kcMedicineMapper.updateById(medicine);
                if (updateQuantity == 0){
                    throw new RuntimeException("修改出库记录失败！");
                }
                int count2 = kcOutintodetialMapper.insert(kcOutintodetial);
                if (count2 > 0){
                    addCount++;
                }
            }
            if (kcDispatch.getMedicineList().size() != addCount){
                throw new RuntimeException("添加出入库调度出库明细失败！");
            }
            addCount = 0;
            for(BaseMedicine baseMedicine: kcDispatch.getMedicineList()){
                KcOutintodetial kcOutintodetial = new KcOutintodetial();
                String crkmx = CodeUtil.createCode("CRKMX");
                kcOutintodetial.setCode(crkmx);
                kcOutintodetial.setTypeId(2);
                kcOutintodetial.setCreateBy(1);
                kcOutintodetial.setCreateDate(new Date());
                kcOutintodetial.setOrderCode(kcDispatch.getCode());
                kcOutintodetial.setMedicineId(baseMedicine.getMedicineId());
                kcOutintodetial.setPrice(baseMedicine.getPurchasePrice());
                kcOutintodetial.setBatchCode(baseMedicine.getBatchCode());
                kcOutintodetial.setProviderId(baseMedicine.getProviderId());
                kcOutintodetial.setToStockMoney(baseMedicine.getPurchasePrice().multiply(BigDecimal.valueOf(baseMedicine.getQuantity())));
                kcOutintodetial.setToStockQuantity(baseMedicine.getQuantity());
                kcOutintodetial.setPrice(baseMedicine.getPurchasePrice());
                kcOutintodetial.setWareHouseId(baseMedicine.getAimStoreHouseId());
                logger.info("getKcDispathList auditingDispatch outkcOutintodetial："+kcOutintodetial);
                KcMedicine medicine = kcMedicineMapper.getMedicineByBatchCode(baseMedicine.getBatchCode(),baseMedicine.getAimStoreHouseId());
                int updateQuantity = 0;
                if (medicine == null){
                    KcMedicine addKcMedicine = new KcMedicine();
                    addKcMedicine.setBatchCode(baseMedicine.getBatchCode());
                    addKcMedicine.setStorehouseId(baseMedicine.getAimStoreHouseId());
                    addKcMedicine.setMedicineId(baseMedicine.getMedicineId());
                    addKcMedicine.setProviderId(baseMedicine.getProviderId());
                    addKcMedicine.setQuantity(baseMedicine.getQuantity());
                    addKcMedicine.setMoney(baseMedicine.getPurchasePrice());
                    addKcMedicine.setTotalPrice(baseMedicine.getPurchasePrice().multiply(BigDecimal.valueOf(baseMedicine.getQuantity())));
                    updateQuantity = kcMedicineMapper.insert(addKcMedicine);
                }else {
                    medicine.setQuantity(medicine.getQuantity() + baseMedicine.getQuantity());
                    updateQuantity = kcMedicineMapper.updateById(medicine);
                }
                if (updateQuantity == 0){
                    throw new RuntimeException("修改出库记录失败！");
                }
                int count2 = kcOutintodetialMapper.insert(kcOutintodetial);
                if (count2 > 0){
                    addCount++;
                }
            }
            if (kcDispatch.getMedicineList().size() != addCount){
                throw new RuntimeException("添加出入库调度入库明细失败！");
            }
            kcDispatch.setOrderStatus(4);
            kcDispatch.setApproverBy(1);
            int approverCount = kcDispatchMapper.updateById(kcDispatch);
            if (approverCount > 0){
                return Message.success();
            }
            throw new RuntimeException("审批调度单失败");
        }
        kcDispatch.setApproverBy(1);
        int approverCount = kcDispatchMapper.updateById(kcDispatch);
        if (approverCount > 0){
            return Message.success();
        }
        throw new RuntimeException("审批调度单失败");
    }

    @Override
    public Message deleteDispatch(Integer id) {
        int i = kcDispatchMapper.deleteById(id);
        if (i > 0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateVoidStatus(int id) {
        UpdateWrapper<KcDispatch> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("voidState", 1)
                .eq("id", id); // 添加ID的条件
        int updateRow = kcDispatchMapper.update(null, updateWrapper);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("作废失败");
    }


}
