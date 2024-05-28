package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CwAccounts;
import com.kgc.entity.CwNumByMonth;
import com.kgc.entity.CwNumByYear;
import com.kgc.vo.CwAccountsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CwAccountsDao extends BaseMapper<CwAccounts> {

    List<CwAccounts> getCwAccountsList(CwAccounts cwAccounts);

    List<CwAccountsVO> getCwAccountsVOList();

    List<CwNumByYear> getCwNumByYear(@Param("year") String year);

    CwNumByMonth getCwNumByMonth(@Param("year") String year, @Param("month") String month);


}
