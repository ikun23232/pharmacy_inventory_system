package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.*;
import com.kgc.dao.CwCgyfDao;
import com.kgc.service.CwAccountsService;
import com.kgc.service.CwCgyfService;
import com.kgc.service.ProcurementOrderService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwCgyfVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * (CwCgyf)表服务实现类
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
@Service("cwCgyfService")
public class CwCgyfServiceImpl extends ServiceImpl<CwCgyfDao, CwCgyf> implements CwCgyfService {

    @Resource
    private CwCgyfDao cwCgyfDao;

    @Resource
    private CwAccountsService cwAccountsService;
    @Resource
    private ProcurementOrderService procurementOrderService;

    @Override
    public Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwCgyf> list = cwCgyfDao.getCwCgyfList(cwCgyf);
        PageInfo<CwCgyf> pageInfo = new PageInfo<>(list);
        if (!list.isEmpty()){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message updateCwCgyf(CwCgyf cwCgyf) {
        CwCgyf updateCwCgyf = new CwCgyf();
        updateCwCgyf.setId(cwCgyf.getId());
        updateCwCgyf.setIsPay(2);
        int isPay = cwCgyfDao.updateById(updateCwCgyf);
        if (isPay > 0){
            return Message.success();
        }
        CgddOrder cgddOrder = new CgddOrder();
        cgddOrder.setId(cwCgyf.getCgddId());
        cgddOrder.setIsPay(1);
        cgddOrder.setPayTime(new Date());
        int isPay1 = procurementOrderService.updateCgddIsPayById(cgddOrder);
        if (isPay1 <= 0){
            return Message.error();
        }
        CwCgyf cwCgyfById = cwCgyfDao.getCwCgyfById(cwCgyf.getId());
        CwAccounts cwAccounts = new CwAccounts();
        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        cwAccounts.setCode(code);
        cwAccounts.setCategoryId(5);
        cwAccounts.setCost(cwCgyfById.getCost());
        cwAccounts.setAccountsCategoryName("采购付款");
        cwAccounts.setCreateTime(new Date());
        cwAccounts.setOrderCode(cwCgyfById.getCode());
        cwAccounts.setCreateBy(cwCgyf.getPayUserId());
        cwAccountsService.addCwAccounts(cwAccounts);
        return Message.error();
    }

    @Override
    public Message getCwCgyfByCode(String code) {
        CwCgyf cwCgyfByCode = cwCgyfDao.getCwCgyfByCode(code);
        if (cwCgyfByCode != null){
            return Message.success(cwCgyfByCode);
        }
        return Message.error();
    }

    @Override
    public CwCgyf getCwCgyfById(int id) {
        return cwCgyfDao.getCwCgyfById(id);
    }

    @Override
    public Message getProviderList() {
        List<BaseProvider> list = cwCgyfDao.getProviderList();
        if (!list.isEmpty()){
            return Message.success(list);
        }
        return Message.error();

    }

    @Override
    public Message getCgddByCode(CgddOrder cgddOrder) {
        CgddOrder cgddByCode = cwCgyfDao.getCgddByCode(cgddOrder);
        if (cgddByCode != null){
            return Message.success(cgddByCode);
        }
        return Message.error("该订单编号没有订单");
    }

    @Override
    public void cwCgyfExcel(HttpServletResponse response) {
        List<CwCgyfVO> listExcel = cwCgyfDao.getCwCgyfVOList();
        try {
            ExeclUtil.write(listExcel, CwCgyfVO.class,response,"采购应付");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addCgyf(CwCgyf cwCgyf) {
        int insert = cwCgyfDao.insert(cwCgyf);
        if (insert >0){
            return Message.success();
        }
        return Message.error("添加失败");
    }
}
