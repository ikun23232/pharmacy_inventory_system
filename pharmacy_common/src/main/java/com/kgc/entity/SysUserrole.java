package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class SysUserrole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色id
     */
      @TableId(value = "userRoleId", type = IdType.AUTO)
    private Integer userRoleId;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 角色id
     */
    @TableField("roleId")
    private Integer roleId;


}
