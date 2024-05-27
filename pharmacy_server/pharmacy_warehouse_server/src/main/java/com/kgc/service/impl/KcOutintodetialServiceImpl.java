package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcDisfromwareMapper;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcOutintodetial;
import com.kgc.dao.KcOutintodetialMapper;
import com.kgc.entity.Message;
import com.kgc.service.KcOutintodetialService;
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
public class KcOutintodetialServiceImpl extends ServiceImpl<KcOutintodetialMapper, KcOutintodetial> implements KcOutintodetialService {

    @Autowired
    private KcOutintodetialMapper kcOutintodetialMapper;


    @Override
    public Message getKcOutinTOdetailList(DispatchVO vo) {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("code", vo.getCode());
        paramsMap.put("type", vo.getType());
        paramsMap.put("startTime", vo.getStartTime());
        paramsMap.put("endTime", vo.getEndTime());
        paramsMap.put("beforeWarhouseId", vo.getBeforeWarehouseId());
        paramsMap.put("batchCode", vo.getBatchCode());


        PageHelper.startPage(vo.getCurrentPageNo(), vo.getPageSize());
        List<KcOutintodetial> kcDisfromwareList = kcOutintodetialMapper.getKcDisfromwareList(paramsMap);
        PageInfo<KcOutintodetial> kcOutintodetialPageInfo = new PageInfo<>(kcDisfromwareList);
        return Message.success(kcOutintodetialPageInfo);
    }

    @Override
    public Message delKcOutinTodetail(int id) {
        int updateRow = kcOutintodetialMapper.deleteById(id);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public void crkmxExcel(DispatchVO vo, HttpServletResponse response) {
//        List<KcOutintodetial> kcOutintodetials = kcOutintodetialMapper.getKcDisfromwareList(new HashMap<>());
//        try {
//            ExeclUtil.writeExcel(kcOutintodetials,response,"出入库明细",KcOutintodetial.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public Message addKcOutinTodetail(KcOutintodetial kcOutintodetial) {
        int isAdd = kcOutintodetialMapper.insert(kcOutintodetial);
        if (isAdd>0){
            return Message.success();
        }
        return Message.error();
    }
}
