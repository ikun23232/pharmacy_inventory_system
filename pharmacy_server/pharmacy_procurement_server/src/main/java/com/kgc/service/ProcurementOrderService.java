package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 15:56
 */
public interface ProcurementOrderService extends IService<CgddOrder> {
    public Message getCgddOrder(CgddOrder cgddOrder, Page page);

    public Message addCgddOrder(CgddOrder cgddOrder);

    public Message deleteById(int id);

    public Message setVoidState(CgddOrder cgddOrder);

    public Message getCgddByCode(CgddOrder cgddOrder);

    public Message updateCgddById(CgddOrder cgddOrder);

    public Message auditingOrder(CgddOrder cgddOrder);

    public void cgddExcel(CgddOrder cgddOrder, HttpServletResponse response);

    Message getCgPayCom(String year, String month);

    Message getCgPayNum(String year);

    Message getCgPayNumList(String year,int pageNum,int pageSize);

    BigDecimal getReferenceAmountByCode(String code);

    int updateCgddIsPayByCode(String code);

    Message addcgyf(CwCgyf cwCgyf);

    int updateCgddIsPayById(CgddOrder cgddOrder);
}
