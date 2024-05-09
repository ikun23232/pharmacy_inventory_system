package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.BaseProviderType;
import com.kgc.entity.Message;
import com.kgc.service.BaseProviderService;
import com.kgc.service.BaseProviderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/base/baseProviderType")
public class BaseProviderTypeController {

    @Autowired
    private BaseProviderTypeService baseProviderTypeService;

    @RequestMapping("getBaseProviderTypeList")
    public Message getBaseProviderTypeList(){
        List<BaseProviderType> list = baseProviderTypeService.list();
        return Message.success(list);
    }

}

