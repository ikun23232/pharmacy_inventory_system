package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.ProcurementOrderMapper;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.ProcurementOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 15:57
 */
@Service
public class ProcurementOrderServiceImpl extends ServiceImpl<ProcurementOrderMapper, CgddOrder> implements ProcurementOrderService {
    @Autowired
    private ProcurementOrderMapper mapper;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Message getCgddOrder(CgddOrder cgddOrder, Page page) {
        logger.debug("cgddOrder:"+cgddOrder);
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date beginDate = inputSdf.parse(cgddOrder.getBeginTime());
            Date endDate = inputSdf.parse(cgddOrder.getEndTime());
           cgddOrder.setBeginTime(outputSdf.format(beginDate));
           cgddOrder.setEndTime(outputSdf.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<CgddOrder> order = mapper.getCgddOrder(cgddOrder);
        PageInfo pageInfo = new PageInfo(order);
        if (order != null){
            return Message.success(pageInfo);
        }
        return Message.error("没有数据");
    }

    @Override
    public Message addCgddOrder(CgddOrder cgddOrder) {
        return null;
    }

    @Override
    public Message deleteById(int id) {
        int count = mapper.deleteById(id);
        if (count > 0){
            return Message.success();
        }
        return Message.error("删除失败！");
    }
}
