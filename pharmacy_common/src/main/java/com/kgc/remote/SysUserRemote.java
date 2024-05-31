package com.kgc.remote;

import com.kgc.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author daidai
 */

public interface SysUserRemote{
    @RequestMapping("/getAuthRole")
    @ResponseBody
    public List<String> getAuthRole(@RequestParam("loginId") Object loginId,@RequestParam("loginType") String loginType);

    @RequestMapping("/getAuthpermission")
    @ResponseBody
    public List<String> getAuthpermission(@RequestParam("loginId") Object loginId,@RequestParam("loginType") String loginType);





}
