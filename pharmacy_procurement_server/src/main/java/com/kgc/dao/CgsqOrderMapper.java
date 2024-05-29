package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface CgsqOrderMapper extends BaseMapper<CgsqOrder> {
    List<CgsqOrder> getCgsqOrderList(Map map);
    List<CgsqOrder> getCgsqOrderByStates(Map map);
    CgsqOrder getCgSqOrderById(int id);
    CgsqOrder getCgsqOrderByCode(CgsqOrder cgsqOrder);

    List<CgsqOrder> getCgsqOrderListByExcel(Map map);

}
