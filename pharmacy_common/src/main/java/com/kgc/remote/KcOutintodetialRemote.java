package com.kgc.remote;

import com.kgc.entity.KcOutintodetial;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface KcOutintodetialRemote {

    @RequestMapping("/addKcOutintodetial")
    @ResponseBody
    Message addKcOutintodetial(@RequestBody KcOutintodetial kcOutintodetial);

}
