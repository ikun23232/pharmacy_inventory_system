package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcInventoryMapper;
import com.kgc.dao.KcInventoryrkMapper;
import com.kgc.dao.KcMedicineMapper;
import com.kgc.entity.*;
import com.kgc.service.IKcInventoryrkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.KcInventoryVo;
import com.kgc.vo.PandianDetailVo;
import com.kgc.vo.PndianRKVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-05-25
 */
@Service
public class KcInventoryrkServiceImpl extends ServiceImpl<KcInventoryrkMapper, KcInventoryrk> implements IKcInventoryrkService {
    @Autowired
    private KcInventoryrkMapper kcInventoryrkMapper;

    @Autowired
    private KcMedicineMapper kcMedicineMapper;
    @Autowired
    private KcInventoryMapper kcInventoryMapper;

    @Override
    public Message getAllCheckRkByPage(KcInventoryrk vo, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<KcInventoryrk> allCheckByPage = kcInventoryrkMapper.getAllCheckRkByPage(vo);
        PageInfo<KcInventoryrk> pageInfo = new PageInfo<>(allCheckByPage);
        return Message.success(pageInfo);
    }

    @Override
    public Message getAllCheckById(Integer id) {
        List<PndianRKVo> allCheckRkexcel = kcInventoryrkMapper.getAllCheckRkexcel(id);
        return Message.success(allCheckRkexcel);
    }

    @Override
    public void getAllCheckRkexcel(KcInventoryrk kcInventoryrk,HttpServletResponse response) {
        List<PndianRKVo> allCheckRkexcel = kcInventoryrkMapper.getAllCheckRkexcel(null);
        try {
            ExeclUtil.write(allCheckRkexcel, PndianRKVo.class,response,"盘点明细");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message approveRK(Integer id,Integer status) {
        KcInventoryrk vo = kcInventoryrkMapper.getMeddicinequantityById(id);
        vo.setIsApproved(status);
        kcInventoryrkMapper.update(vo, new UpdateWrapper<KcInventoryrk>()
                .eq("id", vo.getId())
        );
        if (status==2){
            KcMedicine meddicinequantity = kcInventoryrkMapper.getMeddicinequantity(vo);
            meddicinequantity.setQuantity(meddicinequantity.getQuantity()+vo.getRkQuantity());
            kcMedicineMapper.update(meddicinequantity, new UpdateWrapper<KcMedicine>()
                    .eq("id", meddicinequantity.getId())
            );
        }
        KcInventoryVo kcInventoryVoById = kcInventoryMapper.getKcInventoryVoById(vo.getInventorydetailId());
        kcInventoryVoById.setOrderStatus(4);
        kcInventoryMapper.update(kcInventoryVoById, new UpdateWrapper<KcInventory>()
                .eq("id", kcInventoryVoById.getId())
        );

        return Message.success();
    }
}
