package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.CgsqOrderMapper;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.CgsqOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class CgsqOrderServiceImpl extends ServiceImpl<CgsqOrderMapper, CgsqOrder> implements CgsqOrderService {
    @Autowired
    private CgsqOrderMapper cgsqOrderMapper;

    @Override
    public Message getCgsqOrderList(Map map) {
        Page page =(Page) map.get("page");
        return null;
    }

    @Override
    public Message delCgsqOrderById(int id) {
        int updateRow = cgsqOrderMapper.deleteById(id);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

}
