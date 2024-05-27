package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwXstkDao;
import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import com.kgc.service.CwXstkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
