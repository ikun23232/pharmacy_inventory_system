package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class SysUserrole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色id
     */
      @TableId(value = "userRoleId", type = IdType.AUTO)
    private Integer userroleid;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userid;

    /**
     * 角色id
     */
    @TableField("roleId")
    private Integer roleid;


}
