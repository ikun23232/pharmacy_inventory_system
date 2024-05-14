package com.kgc.entity;

import lombok.*;
import java.io.Serializable;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 9:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {
    private int currentPageNo;
    private int pageSize;
}
