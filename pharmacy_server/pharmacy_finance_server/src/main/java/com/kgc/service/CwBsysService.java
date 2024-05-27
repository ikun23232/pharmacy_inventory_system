package com.kgc.service;

import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;

public interface CwBsysService {

    Message getCwbsysList(CwBsys cwBsys, int pageNum, int pageSize);
}
