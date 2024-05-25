package com.kgc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 10:26
 */
@Data
public class WarningVo implements Serializable {
    private int currentPageNo;
    private int pageSize;
    private String code;
    private String startTime;
    private String endTime;
    private int warningType;
    private int medicineId;
}
