//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CgrkOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CgrkOrderMapper extends BaseMapper<CgrkOrder> {
   List<CgrkOrder> getCgrqOrderList(Map map);
   CgrkOrder getCgrkOrder(int id);
  int approveOrder(@Param("id") Integer id,
                   @Param("effectiveTime") Date effectiveTime,
                   @Param("approverBy") Integer approverBy,
                   @Param("approvalStatus") Integer approvalStatus,
                   @Param("orderStatus") Integer orderStatus,
                    @Param("isAddWarehouse") Integer isAddWarehouse);
}
