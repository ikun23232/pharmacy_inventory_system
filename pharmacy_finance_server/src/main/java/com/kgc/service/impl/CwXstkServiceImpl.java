package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwXstkDao;
import com.kgc.dao.CwXsysMapper;
import com.kgc.entity.*;
import com.kgc.feign.SaleOrderFeign;
import com.kgc.service.CwXstkService;
import com.kgc.service.CwXsysService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwCgyfVO;
import com.kgc.vo.CwXstkVO;
import com.kgc.vo.CwXsysVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CwXstkServiceImpl extends ServiceImpl<CwXstkDao, CwXstk> implements CwXstkService {

    @Autowired
    private CwXstkDao cwXstkDao;

    @Autowired
    private SaleOrderFeign saleOrderFeign;

    @Override
    public Message getXstkList(CwXstk cwXstk, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwXstk> list = cwXstkDao.getXstkList(cwXstk);
        PageInfo<CwXstk> pageInfo = new PageInfo<>(list);
        if (list.size()>0){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message getXsysList(CwXsys cwXsys, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwXsys> list = cwXstkDao.getXsysList(cwXsys);
        PageInfo<CwXsys> pageInfo = new PageInfo<>(list);
        if (list.size()>0){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public void cwXstkExcel(HttpServletResponse response) {
        List<CwXstkVO> listExcel = cwXstkDao.getXstkVOList();
        List<CwXstkVO> listExcels = new ArrayList<>();
        for (CwXstkVO cwXstkVO :listExcel) {
            List<BaseMedicine> baseMedicineList = saleOrderFeign.getSaleOrderDetailListByOrderNo(cwXstkVO.getOriginalOrder());
            cwXstkVO.setBaseMedicineList(baseMedicineList);
            listExcels.add(cwXstkVO);
        }
        try {
            ExeclUtil.write(listExcels, CwXstkVO.class,response,"销售退款");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void cwXsysExcel(HttpServletResponse response) {
        List<CwXsysVO> listExcel = cwXstkDao.getXsysVOList();
        List<CwXsysVO> listExcels = new ArrayList<>();
        for (CwXsysVO cwXsysVO :listExcel) {
            List<BaseMedicine> baseMedicineList = saleOrderFeign.getSaleOrderDetailListByOrderNo(cwXsysVO.getOriginalOrder());
            cwXsysVO.setBaseMedicineList(baseMedicineList);
            listExcels.add(cwXsysVO);
        }

        try {
            ExeclUtil.write(listExcels, CwXsysVO.class,response,"销售应收");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Message getXstkByCode(String code) {
        CwXstk cwXstk = cwXstkDao.getXstkByCode(code);
        if (cwXstk!=null){
            return Message.success(cwXstk);
        }
        return Message.error();
    }

    @Override
    public Message getXsysByCode(String code) {
        CwXsys cwXsys = cwXstkDao.getXsysByCode(code);
        if (cwXsys!=null){
            return Message.success(cwXsys);
        }
        return Message.error();
    }

    @Override
    public Message addCwXstk(CwXstk cwXstk) {
        int count=cwXstkDao.insert(cwXstk);
        if(count>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
