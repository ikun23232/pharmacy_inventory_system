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
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单Id
     */
      @TableId(value = "menuId", type = IdType.AUTO)
    private Integer menuid;

    /**
     * 路由路径
     */
    private String url;

    /**
     * 类型（0: 目录,1:菜单,2:按钮）
     */
    private Integer type;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 启用id默认1启用，2禁用
     */
    @TableField("isUse")
    private Integer isuse;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDateTime updatedate;

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

    /**
     * 父级菜单id
     */
    @TableField("parentId")
    private Integer parentid;

    /**
     * 权限字段
     */
    private String code;

    /**
     * 父级名称
     */
    @TableField("parentName")
    private String parentname;


}
