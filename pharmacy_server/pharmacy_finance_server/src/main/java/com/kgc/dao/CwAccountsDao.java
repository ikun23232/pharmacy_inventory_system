package com.kgc.dao;

import com.kgc.entity.CwAccounts;
import com.kgc.entity.CwNumByMonth;
import com.kgc.entity.CwNumByYear;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CwAccountsDao {

    List<CwAccounts> getCwAccountsList(CwAccounts cwAccounts);

    List<CwNumByYear> getCwNumByYear(@Param("year") String year);

    CwNumByMonth getCwNumByMonth(@Param("year") String year, @Param("month") String month);
}
