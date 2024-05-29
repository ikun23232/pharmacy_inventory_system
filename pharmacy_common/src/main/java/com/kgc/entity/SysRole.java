package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ExcelFiled("角色名称")
    private String rolename;

    /**
     * 1：默认启用 2：禁用 
     */
    private Integer isUse;

    /**
     * 创造时间
     */
    @TableField("createTime")
    @ExcelFiled("创建时间")
    private Date createtime;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    @ExcelFiled("修改时间")
    private Date updatetime;

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

    @ExcelFiled("角色权限码")
    private String code;

    @TableField(exist = false)
    private List<Integer> menuIds = new ArrayList<>();

}
