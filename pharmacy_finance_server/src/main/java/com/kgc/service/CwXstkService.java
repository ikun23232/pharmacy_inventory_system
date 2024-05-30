package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

public interface CwXstkService extends IService<CwXstk> {

    Message getXstkList(CwXstk cwXstk, int pageNum, int pageSize);

    Message getXsysList(CwXsys cwXsys, int pageNum, int pageSize);

    void cwXstkExcel(HttpServletResponse response);

    void cwXsysExcel(HttpServletResponse response);

    Message getXstkByCode(String code);

    Message getXsysByCode(String code);

    Message addCwXstk(CwXstk cwXstk);
}
