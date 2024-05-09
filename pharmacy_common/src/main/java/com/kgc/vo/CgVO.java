package com.kgc.vo;

import com.kgc.entity.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 欧洋宏
 * @create: 2024-05-08 16:49
 **/
@Data
public class CgVO implements Serializable {
    private int currentPageNo;
    private int pageSize;
    private String code;
    private int subject;
    private int type;
    private String startTime;
    private String endTime;
    private int voidState;
    private int approvalStatus;
}
