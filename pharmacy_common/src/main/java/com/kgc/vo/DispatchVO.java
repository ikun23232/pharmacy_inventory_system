package com.kgc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:04
 */
@Data
public class DispatchVO implements Serializable {
    private int currentPageNo;
    private int pageSize;
    private String code;
    private String subject;
    private int AimWarehouseId;
    private int beforeWarehouseId;
    private String startTime;
    private String endTime;
    private int voidState;
    private int approvalStatus;
    private int orderStatus;
}
