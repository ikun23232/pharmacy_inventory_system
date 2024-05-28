package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcAlarm;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.vo.KcAlarmVO;
import com.kgc.vo.WarningVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 10:25
 */
public interface KcAlarmService extends IService<KcAlarm> {
    Message getKcAlarmList(WarningVo warningVo);

    Message deleteAlarmList(Integer id);

    Message updateRemind(Integer id);

    void excelKcAlarm(KcAlarmVO kcAlarmVO, HttpServletResponse response);

    public void checkWarning();

    Message getAlarmCount();
}
