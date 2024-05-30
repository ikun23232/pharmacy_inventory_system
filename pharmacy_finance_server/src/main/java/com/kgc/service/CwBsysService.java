package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;

public interface CwBsysService extends IService<CwBsys> {

    Message getCwbsysList(CwBsys cwBsys, int pageNum, int pageSize);

    Message getCwbsysByCode(String code);
    Message getStorehouseList();

    void cwbsysExcel(HttpServletResponse response);

    Message addCwbsys(CwBsys cwBsys);
}
