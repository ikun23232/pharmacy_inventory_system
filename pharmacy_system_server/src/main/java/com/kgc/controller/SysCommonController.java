package com.kgc.controller;

import com.kgc.utils.Md5Util;
import com.kgc.utils.ReplayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-05-07 09:13
 **/
@RestController
@RequestMapping("/sysCommon")
public class SysCommonController {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private ReplayUtil replayUtil;

    @RequestMapping("/getUUID")
    public String getUUID(){
        replayUtil.checkRandom("");
        String random = replayUtil.getRandom();
        String md5String = Md5Util.getMD5String(random);
        return md5String;
    }
}
