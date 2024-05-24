package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.KcAlarmMapper;
import com.kgc.entity.KcAlarm;
import com.kgc.entity.Message;
import com.kgc.service.KcAlarmService;
import com.kgc.vo.WarningVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 10:28
 */
@Service
public class KcAlarmServiceImpl extends ServiceImpl<KcAlarmMapper, KcAlarm> implements KcAlarmService {
    @Autowired
    private KcAlarmMapper kcAlarmMapper;
    @Override
    public Message getKcAlarmList(WarningVo warningVo) {
        PageHelper.startPage(warningVo.getCurrentPageNo(), warningVo.getPageSize());
        List<KcAlarm> kcAlarmList = kcAlarmMapper.getKcAlarmList(warningVo);
        PageInfo pageInfo = new PageInfo(kcAlarmList);
        return Message.success(pageInfo);
    }

    @Override
    public Message deleteAlarmList(Integer id) {
        int i = kcAlarmMapper.deleteById(id);
        if (i > 0){
            return Message.success();
        }
        return Message.error("删除成功!");
    }

    @Override
    public Message updateRemind(Integer id) {
        KcAlarm kcAlarm = new KcAlarm();
        kcAlarm.setId(id);
        kcAlarm.setIsRemind(1);
        kcAlarm.setHandleDate(new Date());
        kcAlarm.setHandleBy(1);
        kcAlarm.setIsKnow(1);
        int i = kcAlarmMapper.updateById(kcAlarm);
        if (i > 0){
            return Message.success();
        }
        return Message.error("处理失败！");
    }
}
