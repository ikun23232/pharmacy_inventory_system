package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.KcDisfromware;
import com.kgc.dao.KcDisfromwareMapper;
import com.kgc.entity.Message;
import com.kgc.service.KcDisfromwareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class KcDisfromwareServiceImpl extends ServiceImpl<KcDisfromwareMapper, KcDisfromware> implements KcDisfromwareService {
    @Autowired
    private KcDisfromwareMapper kcDisfromwareMapper;

    @Override
    public Message getKcDisfromwareList(DispatchVO vo) {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("subject", vo.getSubject());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("endTime", vo.getEndTime());
        paramsMap.put("fowardWarhouseId", vo.getAimWarehouseId());
        paramsMap.put("beforeWarhouseId", vo.getBeforeWarehouseId());
        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<KcDisfromware> kcDisfromwareList1 = kcDisfromwareMapper.getKcDisfromwareList(paramsMap);
        PageInfo<KcDisfromware> kcDisfromwarePageInfo = new PageInfo<>(kcDisfromwareList1);
        return Message.success(kcDisfromwarePageInfo);
    }

    @Override
    public Message getKcDisfromwareOrder(int id) {
        KcDisfromware kcDisfromwareOrder = kcDisfromwareMapper.getKcDisfromwareOrder(id);
        return Message.success(kcDisfromwareOrder);
    }

    @Override
    public Message delKcDisfromware(int id) {
        int updateRow = kcDisfromwareMapper.deleteById(id);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败!");
    }

    @Override
    public void ddckExcel(KcDisfromware kcDisfromware, HttpServletResponse response) {
        List<KcDisfromware> kcDisfromwareList = kcDisfromwareMapper.getKcDisfromwareListByExcel();
        try {
            ExeclUtil.write(kcDisfromwareList,KcDisfromware.class,response,"调度出库");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
