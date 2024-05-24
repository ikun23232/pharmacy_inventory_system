package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwBsysDao;
import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;
import com.kgc.service.CwBsysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CwBsysServiceImpl implements CwBsysService {

    @Autowired
    private CwBsysDao cwBsysDao;

    @Override
    public Message getCwbsysList(CwBsys cwBsys, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwBsys> list=cwBsysDao.getCwbsysList(cwBsys);
        PageInfo<CwBsys> pageInfo=new PageInfo<>(list);
        if (list!=null){
            return Message.success(pageInfo);
        }
        return Message.error();
    }
}
