package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/05/07/ 10:57
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseProviderType {
    /**
     * 供应商类别主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 供应商类别名
     */
    @TableField("type")
    private String type;
}
