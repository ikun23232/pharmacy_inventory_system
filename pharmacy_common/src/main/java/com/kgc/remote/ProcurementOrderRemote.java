package com.kgc.remote;

import com.kgc.entity.CgddOrder;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ProcurementOrderRemote {

    @RequestMapping("updateCgddIsPayByIdFeign")
    @ResponseBody
    Message updateCgddIsPayById(@RequestBody CgddOrder cgddOrder);
}
