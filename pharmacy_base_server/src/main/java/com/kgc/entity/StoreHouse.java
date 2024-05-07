package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 8:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreHouse implements Serializable {
    private Integer id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 仓库容量
     */
    private String capacity;

    /**
     * 负责人
     */
    private Integer userId;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    private Integer isDel;

    /**
     * 仓库编号
     */
    private String code;
}
