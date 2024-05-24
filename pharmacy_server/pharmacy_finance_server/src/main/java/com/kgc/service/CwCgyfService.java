package com.kgc.service;


import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;

/**
 * (CwCgyf)表服务接口
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
public interface CwCgyfService {

    Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize);

    Message getCgddByCode(CgddOrder cgddOrder);

}
