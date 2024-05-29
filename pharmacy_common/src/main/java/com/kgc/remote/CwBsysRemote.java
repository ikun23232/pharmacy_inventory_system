package com.kgc.remote;

import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CwBsysRemote {

    @RequestMapping("addCwbsysFeign")
    @ResponseBody
    Message addCwbsys(@RequestBody CwBsys cwBsys);
}
