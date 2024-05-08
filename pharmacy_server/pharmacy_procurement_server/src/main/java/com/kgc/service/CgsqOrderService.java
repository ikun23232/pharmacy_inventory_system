package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface CgsqOrderService extends IService<CgsqOrder> {

    Message getCgsqOrderList(Map map);
    Message delCgsqOrderById(int id);

}
