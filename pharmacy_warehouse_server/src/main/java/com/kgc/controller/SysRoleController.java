package com.kgc.controller;

import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.SysUser;
import com.kgc.feign.SysUserRoleFeign;
import com.kgc.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author daidai
 */
@Controller
@RequestMapping("/warehouse")
public class SysRoleController {

    @Autowired
    private SysUserRoleFeign sysUserRoleFeign;
    @RequestMapping("/getAllKcAdmin")
    @ResponseBody
    public Message getAllKcAdmin(){
        List<SysUser> allKcAdmin = sysUserRoleFeign.getAllKcAdmin();
        return Message.success(allKcAdmin);
    }
}
