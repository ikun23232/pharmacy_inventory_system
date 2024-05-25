package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcAlarm;
import com.kgc.vo.WarningVo;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 10:09
 */
public interface KcAlarmMapper extends BaseMapper<KcAlarm> {
    List<KcAlarm> getKcAlarmList(WarningVo warningVo);
}
