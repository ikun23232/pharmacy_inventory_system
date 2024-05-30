package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;

public interface CwAccountsService extends IService<CwAccounts> {

    Message getCwAccountsList(CwAccounts cwAccounts, int pageNum, int pageSize);

    Message getCwNumByYear(String year);

    Message getCwNumByMonth(String year, String month);

    void cwAccountsExcel(HttpServletResponse response);

    int addCwAccounts(CwAccounts cwAccounts);

    Message insertCwAccounts(CwAccounts cwAccounts);

}
