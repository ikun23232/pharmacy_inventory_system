package com.kgc.remote;

import com.kgc.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:16
 */
public interface BaseMedineRemote {

    @RequestMapping("getMedicineListByCode")
    @ResponseBody
    public Message getMedicineListByCode(@Param("code") String code);

    @RequestMapping("getMedicineVOListByCode")
    @ResponseBody
    Message getMedicineVOListByCode(@Param("code") String code);

}
