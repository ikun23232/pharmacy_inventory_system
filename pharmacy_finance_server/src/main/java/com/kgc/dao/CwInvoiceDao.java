package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CwCategory;
import com.kgc.entity.CwInvoice;
import com.kgc.vo.CwInvoiceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CwInvoiceDao extends BaseMapper<CwInvoice> {

    List<CwInvoice> getCwInvoice(CwInvoice cwInvoice);

    CwInvoice getCwInvoiceByCode(@Param("code") String code);
    List<CwCategory> getCategoryString();

    List<CwInvoiceVO> getCwInvoiceVO();
}
