package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwAccountsDao;
import com.kgc.entity.CwAccounts;
import com.kgc.entity.CwNumByMonth;
import com.kgc.entity.CwNumByYear;
import com.kgc.entity.Message;
import com.kgc.service.CwAccountsService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwAccountsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class CwAccountsServiceImpl extends ServiceImpl<CwAccountsDao, CwAccounts> implements CwAccountsService {

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

    @Override
    public void cwAccountsExcel(HttpServletResponse response) {
        List<CwAccountsVO> listExcel = cwAccountsDao.getCwAccountsVOList();
        try {
            ExeclUtil.write(listExcel, CwAccountsVO.class,response,"流水详情");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Message addCwAccounts(CwAccounts cwAccounts) {
        System.out.println(cwAccounts);
        log.debug("cwAccountssss:"+cwAccounts);
        int isAdd = cwAccountsDao.insert(cwAccounts);
        if (isAdd > 0){
            return Message.success(isAdd);

        }
        return Message.error();
    }
    @Override
    public Message insertCwAccounts(CwAccounts cwAccounts) {
        int count=cwAccountsDao.insert(cwAccounts);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }
}
