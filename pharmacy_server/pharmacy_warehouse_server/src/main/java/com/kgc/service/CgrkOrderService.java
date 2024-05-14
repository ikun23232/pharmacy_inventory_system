package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.Message;
import com.kgc.vo.CgVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface CgrkOrderService extends IService<CgrkOrder> {

    Message getCgrkOrderService(CgVO cgVO);
    /**
     * 单个删除
     * @param id
     * @return
     */
    Message delCgrkOrderById(int id);

    /**
     * 添加采购订单
     * @param cgrqOrder
     * @return
     */
    Message addCgrkOrder(CgrkOrder cgrqOrder);

    /**
     * 更新采购订单
     * @param cgrqOrder
     * @return
     */

    Message updateCgrkOrder(CgrkOrder cgrqOrder);

    /**
     * 获得采购订单
     * @param id
     * @return
     */
    Message getCgrqOrder(int id);

    /**
     * 作废订单
     * @param id
     * @return
     */
    Message voidCgrqOrder(int id);
    /**
     * 审核订单
     * @param id
     * @return
     */
    Message approveCgrqOrder(int id,String approveRemark,int approveMent);

}
