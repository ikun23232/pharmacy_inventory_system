package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcDispatchMapper;
import com.kgc.entity.KcDispatch;
import com.kgc.entity.Message;
import com.kgc.service.KcDispatchService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:10
 */
@Service
public class KcDispatchServiceImpl extends ServiceImpl<KcDispatchMapper, KcDispatch> implements KcDispatchService {

    @Autowired
    private KcDispatchMapper kcDispatchMapper;
    @Override
    public Message getKcDispathList(DispatchVO dispatchVO) {
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date beginDate = inputSdf.parse(dispatchVO.getStartTime());
            Date endDate = inputSdf.parse(dispatchVO.getEndTime());
            dispatchVO.setStartTime(outputSdf.format(beginDate));
            dispatchVO.setEndTime(outputSdf.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(dispatchVO.getCurrentPageNo(), dispatchVO.getPageSize());
        List<KcDispatch> kcDispathList = kcDispatchMapper.getKcDispathList(dispatchVO);
        PageInfo pageInfo = new PageInfo(kcDispathList);
        return Message.success(pageInfo);
    }
}
