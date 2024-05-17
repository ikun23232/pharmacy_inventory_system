//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CgrkOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CgrkOrderMapper extends BaseMapper<CgrkOrder> {
   List<CgrkOrder> getCgrqOrderList(Map map);

}
