package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcPage {
    private String code;
    private String beginTime;
    private String endTime;
    private int orderStatus;
    private int providerId;
    private int type;
    private int pageNum;
    private int pageSize;
}
