package com.kgc.vo;

import com.kgc.entity.SysLoginLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 登录日志 Vo
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLoginLogVo extends SysLoginLog {
    private String startTime;
    private String endTime;
    private String current;
}
