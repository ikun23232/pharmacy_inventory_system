package com.kgc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CgsqOrderMapper;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.CgsqOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CgsqOrderServiceImpl extends ServiceImpl<CgsqOrderMapper, CgsqOrder> implements CgsqOrderService {
    @Autowired
    private CgsqOrderMapper cgsqOrderMapper;

    @Override
    public Message getCgsqOrderList(CgVO vo) {
//        CgVO vo = JSON.parseObject(JSON.toJSONString(map), CgVO.class);

//        String code = JSON.parseObject(JSON.toJSONString(map.get("code")), String.class);
//        int subject = JSON.parseObject(JSON.toJSONString(map.get("subject")), Integer.class);
//        int type = JSON.parseObject(JSON.toJSONString(map.get("type")), Integer.class);
//        String startTime = JSON.parseObject(JSON.toJSONString(map.get("startTime")), String.class);
//        String endTime = JSON.parseObject(JSON.toJSONString(map.get("endTime")), String.class);
//        int voidState = JSON.parseObject(JSON.toJSONString(map.get("voidState")), Integer.class);
        Map paramsMap= new HashMap<String,Object>();
        paramsMap.put("code",vo.getCode());
        paramsMap.put("subject",vo.getSubject());
        paramsMap.put("type",vo.getType());
        paramsMap.put("startTime",vo.getStartTime());
        paramsMap.put("voidState",vo.getVoidState());
        paramsMap.put("endTime",vo.getEndTime());
        PageHelper.startPage(vo.getCurrentPageNo(),vo.getPageSize());
        List<CgsqOrder> cgsqOrderList = cgsqOrderMapper.getCgsqOrderList(paramsMap);
        PageInfo<CgsqOrder> pageInfo=new PageInfo<>(cgsqOrderList);
        return Message.success(pageInfo);
    }

    @Override
    public Message delCgsqOrderById(int id) {
        int updateRow = cgsqOrderMapper.deleteById(id);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

}