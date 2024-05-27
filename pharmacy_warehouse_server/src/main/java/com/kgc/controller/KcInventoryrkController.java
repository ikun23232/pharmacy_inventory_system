package com.kgc.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.KcInventorydetail;
import com.kgc.entity.KcInventoryrk;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.IKcInventoryrkService;
import com.kgc.vo.KcInventoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-05-25
 */
@RestController
@RequestMapping("/warehouse")
public class KcInventoryrkController {
    @Autowired
    private IKcInventoryrkService iKcInventoryrkService;

    @RequestMapping("/check/getAllCheckRkByPage")
    public Message getAllCheckRkByPage(@RequestBody KcInventoryrk vo ) {
        int _currentPageNo = 1;

        if (vo.getCurrent() != null && !"".equals(vo.getCurrent())) {
            _currentPageNo = Integer.parseInt(vo.getCurrent());
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        Message message = iKcInventoryrkService.getAllCheckRkByPage(vo, page);
        return message;
    }
    @SaCheckPermission("kc:inventroyRK:dekete")//必须拥有该权限可访问
    @RequestMapping("/check/deleteCheckRK")
    public Message deleteCheckRK(@RequestBody Integer[] ids) {

        iKcInventoryrkService.removeByIds(Arrays.asList(ids));
        return Message.success("操作成功");
    }

    @RequestMapping("/check/approveRK")
    public Message approveRK(Integer id ,Integer status) {
        Message message = iKcInventoryrkService.approveRK(id,status);
        return message;
    }

    @SaCheckPermission("kc:inventroyRK:excel")//必须拥有该权限可访问
    @RequestMapping("/check/getAllCheckRkexcel")
    public void getAllCheckRkexcel(@RequestBody KcInventoryrk kcInventoryrk , HttpServletResponse response) {
         iKcInventoryrkService.getAllCheckRkexcel(kcInventoryrk, response);
    }
    @RequestMapping("/check/getAllCheckById")
    public Message getAllCheckById(Integer id) {
        Message allCheckById = iKcInventoryrkService.getAllCheckById(id);
        return allCheckById;
    }

}
