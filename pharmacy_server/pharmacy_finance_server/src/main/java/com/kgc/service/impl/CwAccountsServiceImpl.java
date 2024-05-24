package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwAccountsDao;
import com.kgc.entity.CwAccounts;
import com.kgc.entity.CwNumByMonth;
import com.kgc.entity.CwNumByYear;
import com.kgc.entity.Message;
import com.kgc.service.CwAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CwAccountsServiceImpl implements CwAccountsService {

    @Autowired
    private CwAccountsDao cwAccountsDao;

    @Override
    public Message getCwAccountsList(CwAccounts cwAccounts, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwAccounts> list = cwAccountsDao.getCwAccountsList(cwAccounts);
        PageInfo<CwAccounts> pageInfo = new PageInfo<>(list);
        if (list.size() > 0){
            return Message.success(pageInfo);
        }
        return Message.error();

    }
    @Override
    public Message getCwNumByYear(String year) {
        List<CwNumByYear> list = cwAccountsDao.getCwNumByYear(year);
        if (list.size() > 0){
            return Message.success(list);
        }
        return Message.error();
    }

    @Override
    public Message getCwNumByMonth(String year, String month) {
        CwNumByMonth cwNumByMonth = cwAccountsDao.getCwNumByMonth(year, month);
        if (cwNumByMonth != null){
            return Message.success(cwNumByMonth);
        }
        return Message.error();
    }
}
