package com.kgc.remote;

import com.kgc.entity.SysLogManage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author daidai
 */
public interface SysLogRemote {
    @RequestMapping("/addLogManagement")
    @ResponseBody
    public boolean addLogManagement(@RequestBody SysLogManage sysLogManage);
}
