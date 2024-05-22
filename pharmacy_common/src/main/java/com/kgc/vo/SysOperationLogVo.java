package com.kgc.vo;

import com.kgc.entity.SysLogManage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 操作日志 Vo
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysOperationLogVo extends SysLogManage {
    private String startTime;
    private String endTime;
    private String current;
}
