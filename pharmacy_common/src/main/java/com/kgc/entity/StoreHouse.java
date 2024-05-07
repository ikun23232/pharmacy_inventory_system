package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 8:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StoreHouse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("userId")
    private Integer userid;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private LocalDateTime updatetime;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;

    /**
     * 仓库编号
     */
    private String code;
}

