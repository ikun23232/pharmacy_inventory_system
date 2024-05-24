package com.kgc.service;

import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;

public interface CwXstkService {

    Message getXstkList(CwXstk cwXstk, int pageNum, int pageSize);

    Message getXsysList(CwXsys cwXsys, int pageNum, int pageSize);
}
