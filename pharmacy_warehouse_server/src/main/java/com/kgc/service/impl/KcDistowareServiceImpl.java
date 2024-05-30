package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcDisfromwareMapper;
import com.kgc.dao.KcDistowareMapper;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDistoware;
import com.kgc.entity.Message;
import com.kgc.service.KcDistowareService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.DispatchToWareVO;
import com.kgc.vo.DispatchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class KcDistowareServiceImpl extends ServiceImpl<KcDistowareMapper, KcDistoware> implements KcDistowareService {
    @Autowired
    private KcDistowareMapper kcDistowareMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Message getKcDistowareList(DispatchVO vo) {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("subject", vo.getSubject());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("endTime", vo.getEndTime());
//        paramsMap.put("approvalStatus", vo.getApprovalStatus());
        paramsMap.put("fowardWarhouseId", vo.getAimWarehouseId());
        paramsMap.put("beforeWarhouseId", vo.getBeforeWarehouseId());
        logger.debug("KcDistowareServiceImpl getKcDistowareList paramsMap:"+paramsMap);
        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<DispatchToWareVO> kcDisfromwareList1 = kcDistowareMapper.getKcDistowareList(paramsMap);
        PageInfo<DispatchToWareVO> kcDisfromwarePageInfo = new PageInfo<>(kcDisfromwareList1);
        return Message.success(kcDisfromwarePageInfo);
    }

    @Override
    public Message getKcDistowareOrder(int id) {
        DispatchToWareVO kcDisfromwareOrder = kcDistowareMapper.getKcDistowareOrder(id);
        return Message.success(kcDisfromwareOrder);
    }

    @Override
    public Message delKcDistoware(int id) {
        int updateRow = kcDistowareMapper.deleteById(id);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败!");
    }

    @Override
    public void ddrkExcel(KcDistoware kcDistoware, HttpServletResponse response) {
        List<KcDistoware> kcDisfromwareList = kcDistowareMapper.getKcDistowareListByExcel(new HashMap<>());
        try {
            ExeclUtil.write(kcDisfromwareList,KcDistoware.class,response,"调度出库");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
