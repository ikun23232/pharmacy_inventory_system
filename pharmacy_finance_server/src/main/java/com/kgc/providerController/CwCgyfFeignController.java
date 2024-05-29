package com.kgc.providerController;

import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;
import com.kgc.remote.CwCgyfRemote;
import com.kgc.service.CwCgyfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/29 14:32
 */
@Controller
public class CwCgyfFeignController implements CwCgyfRemote {

    @Autowired
    private CwCgyfService cwCgyfService;
    @Override
    public Message addCgyf(CwCgyf cwCgyf) {
        Message message = cwCgyfService.addCgyf(cwCgyf);
        return message;
    }
}
