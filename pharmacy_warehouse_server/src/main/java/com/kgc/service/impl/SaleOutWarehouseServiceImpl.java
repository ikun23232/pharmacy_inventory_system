package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SaleOutWarehouseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.feign.SaleOrderFeign;
import com.kgc.service.SaleOutWarehouseService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.KcSalefromwareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class SaleOutWarehouseServiceImpl extends ServiceImpl<SaleOutWarehouseMapper, KcSalefromware> implements SaleOutWarehouseService {

    @Autowired
    private SaleOutWarehouseMapper saleOutWarehouseMapper;
    @Autowired
    private SaleOrderFeign saleOrderFeign;

    @Override
    public Message getSaleOutWarehouseListByPage(KcSalefromware kcSalefromware) {
        PageHelper.startPage(kcSalefromware.getCurrentPage(),5);
        List<KcSalefromware> kcSalefromwareList=saleOutWarehouseMapper.getSaleOutWarehouseListByPage(kcSalefromware);
        PageInfo<KcSalefromware> pageInfo=new PageInfo<>(kcSalefromwareList);
        if(pageInfo!=null) {
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message deleteSaleOutWarehouseOrder(String code) {
        QueryWrapper<KcSalefromware> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("code",code);
        int count=saleOutWarehouseMapper.delete(queryWrapper);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public void saleOutWarehouseExcel(KcSalefromware kcSalefromware, HttpServletResponse response) {
        List<KcSalefromwareVo> kcSalefromwareList=saleOutWarehouseMapper.getSaleOutWarehouseList(kcSalefromware);
        for (KcSalefromwareVo KcSalefromwareVo :kcSalefromwareList) {
            Message message = saleOrderFeign.getSaleOrderDetailByOrderNo(KcSalefromwareVo.getOrderNo());
            KcSalefromwareVo.setBaseMedicineList((List<BaseMedicine>) message.getData());
        }
        try {
            ExeclUtil.write(kcSalefromwareList, KcSalefromwareVo.class,response,"销售出库订单");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addSaleOutWarehouse(KcSalefromware kcSalefromware) {
        int count=saleOutWarehouseMapper.insert(kcSalefromware);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }
}
