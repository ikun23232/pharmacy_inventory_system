package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;

public interface CwXsysService extends IService<CwXsys> {

    public Message addCwXsys(CwXsys cwXsys);
}
