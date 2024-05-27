package com.kgc.controller;

import com.kgc.service.CheckHintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/procurement")
public class CheckHintController {

    @Autowired
    private CheckHintService checkHintService;

    @RequestMapping("/getCheckHintList")
    public Object getCheckHintList()
    {
        return checkHintService.getCheckHintList();
    }

}
