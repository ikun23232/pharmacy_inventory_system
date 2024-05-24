package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.dao.CwCgyfDao;
import com.kgc.entity.Message;
import com.kgc.service.CwCgyfService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CwCgyf)表服务实现类
 *
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
@Service("cwCgyfService")
public class CwCgyfServiceImpl implements CwCgyfService {

    @Resource
    private CwCgyfDao cwCgyfDao;

    @Override
    public Message getCwCgyfList(CwCgyf cwCgyf,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwCgyf> list = cwCgyfDao.getCwCgyfList(cwCgyf);
        PageInfo<CwCgyf> pageInfo = new PageInfo<>(list);
        if (list.size()>0){
            return Message.success(pageInfo);
        }
        return Message.error();
    }

    @Override
    public Message getCgddByCode(CgddOrder cgddOrder) {
        CgddOrder cgddByCode = cwCgyfDao.getCgddByCode(cgddOrder);
        if (cgddByCode != null){
            return Message.success(cgddByCode);
        }
        return Message.error("该订单编号没有订单");
    }
}
