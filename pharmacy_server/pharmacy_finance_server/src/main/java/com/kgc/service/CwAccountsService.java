package com.kgc.service;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.Message;

public interface CwAccountsService {

    Message getCwAccountsList(CwAccounts cwAccounts, int pageNum, int pageSize);

    Message getCwNumByYear(String year);

    Message getCwNumByMonth(String year, String month);

}
