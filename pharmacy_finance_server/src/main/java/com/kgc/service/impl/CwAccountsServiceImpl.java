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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
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
    public int addCwAccounts(CwAccounts cwAccounts) {
        int isAdd = cwAccountsDao.insert(cwAccounts);
        return isAdd;
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

    @Override
    public boolean saveBatch(Collection<CwAccounts> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<CwAccounts> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<CwAccounts> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(CwAccounts entity) {
        return false;
    }

    @Override
    public CwAccounts getOne(Wrapper<CwAccounts> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<CwAccounts> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<CwAccounts> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public CwAccountsDao getBaseMapper() {
        return null;
    }

    @Override
    public Class<CwAccounts> getEntityClass() {
        return null;
    }
}
