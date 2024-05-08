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
    private int currentPageNo;
    private int pageSize;
    private int totalCount;
    private int totalPageCount;
    private Object data;



}
