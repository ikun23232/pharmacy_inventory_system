package com.kgc.dao;

import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;

import java.util.List;

public interface CwXstkDao {

    List<CwXstk> getXstkList(CwXstk cwXstk);


    List<CwXsys> getXsysList(CwXsys cwXsys);

}
