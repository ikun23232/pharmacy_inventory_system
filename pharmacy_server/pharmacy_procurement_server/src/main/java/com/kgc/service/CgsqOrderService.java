package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.vo.CgVO;

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
    /**
     * 分页查询
     * @param cgVO
     * @return
     */
    Message getCgsqOrderList(CgVO cgVO);
    Message delCgsqOrderById(int id);



}
