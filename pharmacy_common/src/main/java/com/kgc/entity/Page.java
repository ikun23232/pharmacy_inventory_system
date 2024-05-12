package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer currentPageNo;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPageCount;
    private Object data;



}
