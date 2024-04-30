package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String accountscategoryname;

    /**
     * 流水级别
     */
    private String type;

    /**
     * 流水类别父级Id
     */
    @TableField("parentId")
    private String parentid;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private String isdel;


}
