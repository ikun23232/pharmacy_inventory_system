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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
      @TableId(value = "roleId", type = IdType.AUTO)
    private Integer roleid;

    /**
     * 角色名字
     */
    @TableField("roleName")
    private String rolename;

    /**
     * 1：默认启用 2：禁用 
     */
    @TableField("isUse")
    private Integer isuse;

    /**
     * 创造时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private LocalDateTime updatetime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;


}
