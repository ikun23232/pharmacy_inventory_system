package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流水类别名
     */
    @TableField("accountsCategoryName")
    private String accountsCategoryName;

    /**
     * 流水级别
     */
    private String type;

    /**
     * 流水类别父级Id
     */
    @TableField("parentId")
    private String parentId;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private String isDel;


}
