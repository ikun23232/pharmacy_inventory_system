package com.kgc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CheckHint {

    private String tableName;

    private String code;

    private String documenterName;

    private String path;

    private String component;
}
