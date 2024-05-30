package com.kgc.remote;

import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CwXsysRemote {
    @RequestMapping("/addCwXsys")
    @ResponseBody
    public Message addCwXsys(@RequestBody CwXsys cwXsys);


}
