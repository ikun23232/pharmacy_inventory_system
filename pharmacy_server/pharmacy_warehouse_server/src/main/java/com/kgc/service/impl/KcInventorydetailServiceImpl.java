package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.entity.KcInventorydetail;
import com.kgc.dao.KcInventorydetailMapper;
import com.kgc.entity.Message;
import com.kgc.service.IKcInventorydetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.PandianMedicineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-05-21
 */
@Service
public class KcInventorydetailServiceImpl extends ServiceImpl<KcInventorydetailMapper, KcInventorydetail> implements IKcInventorydetailService {


    @Autowired
    private KcInventorydetailMapper kcInventorydetailMapper;
    @Autowired
    private KcInventoryMapper kcInventoryMapper;



    @Override
    public boolean updateCheck(KcInventorydetail kcInventorydetail,Integer id) {

        KcInventorydetail kcInventoryByMeandwaandpro = kcInventorydetailMapper.getKcInventoryByMeandwaandpro(kcInventorydetail.getStorehouseId(), kcInventorydetail.getMedicineId(), kcInventorydetail.getProviderId(),id);
        if (kcInventoryByMeandwaandpro!=null) {
            // 如果数据已存在，执行更新操作
            int inventoryId = kcInventorydetailMapper.update(kcInventorydetail, new UpdateWrapper<KcInventorydetail>()
                    .eq("inventoryId", id)
                    .eq("storehouseId", kcInventorydetail.getStorehouseId())
                    .eq("medicineId", kcInventorydetail.getMedicineId())
                    .eq("providerId", kcInventorydetail.getProviderId())
            );

            if (inventoryId>0) {
                return true;
            } else {
                return false;
            }
        } else {
            // 如果数据不存在，执行插入操作
            kcInventorydetail.setIsOther(1);
            kcInventorydetail.setInventoryId(id);
            boolean saved = this.save(kcInventorydetail);
            if (saved) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public Message getKcInventoryUnitName(Integer warehouseId, Integer medecineId) {
        String kcInventoryUnitName = kcInventorydetailMapper.getKcInventoryUnitName(warehouseId, medecineId);
        return Message.success(kcInventoryUnitName);
    }

    @Override
    public Message getKcInventoryDetailByIdwithother(Integer id) {
        List<KcInventorydetail> kcInventoryDetailByIdwithother = kcInventorydetailMapper.getKcInventoryDetailByIdwithother(id);
        return Message.success(kcInventoryDetailByIdwithother);
    }

    @Override
    public Message getAllKcInventoryDetailById(Integer id) {

        KcInventoryVo kcInventoryVo = kcInventoryMapper.getKcInventoryVoById(id);
        List<PandianMedicineVo> allKcInventoryDetailById = kcInventorydetailMapper.getAllKcInventoryDetailById(id);
        kcInventoryVo.setPandianMedicineList(allKcInventoryDetailById);
        return Message.success(kcInventoryVo);

    }
}



