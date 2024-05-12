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

    /**
     * 单个删除
     * @param id
     * @return
     */
    Message delCgsqOrderById(int id);

    /**
     * 添加采购订单
     * @param cgsqOrder
     * @return
     */
    Message addCgsqOrder(CgsqOrder cgsqOrder);

    /**
     * 更新采购订单
     * @param cgsqOrder
     * @return
     */

    Message updateCgsqOrder(CgsqOrder cgsqOrder);

    /**
     * 获得采购订单
     * @param id
     * @return
     */
    Message getCgsqOrder(int id);

    /**
     * 作废订单
     * @param id
     * @return
     */
    Message voidCgsqOrder(int id);
    /**
     * 审核订单
     * @param id
     * @return
     */
    Message approveCgsqOrder(int id,String approveRemark,int approveMent);










}
