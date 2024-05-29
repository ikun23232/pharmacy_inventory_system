package com.kgc.remote;

import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/29 14:32
 */
public interface CwCgyfRemote {

    @RequestMapping("addCgyf")
    @ResponseBody
    Message addCgyf(@RequestBody CwCgyf cwCgyf);
}
