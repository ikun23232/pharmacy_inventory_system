package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwXstkDao;
import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import com.kgc.service.CwXstkService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwCgyfVO;
import com.kgc.vo.CwXstkVO;
import com.kgc.vo.CwXsysVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class CwXstkServiceImpl implements CwXstkService {

    @Autowired
    private CwXstkDao cwXstkDao;

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
        try {
            ExeclUtil.write(listExcel, CwXstkVO.class,response,"销售退款");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cwXsysExcel(HttpServletResponse response) {
        List<CwXsysVO> listExcel = cwXstkDao.getXsysVOList();
        try {
            ExeclUtil.write(listExcel, CwXsysVO.class,response,"销售应收");
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
}
