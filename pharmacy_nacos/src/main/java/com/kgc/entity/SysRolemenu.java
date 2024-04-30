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
public class SysRolemenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限id
     */
      @TableId(value = "roleMenuId", type = IdType.AUTO)
    private Integer rolemenuid;

    /**
     * 菜单Id
     */
    @TableField("menuId")
    private Integer menuid;

    /**
     * 角色Id
     */
    @TableField("roleId")
    private Integer roleid;


}
