package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BaseStorehouse implements Serializable {

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
