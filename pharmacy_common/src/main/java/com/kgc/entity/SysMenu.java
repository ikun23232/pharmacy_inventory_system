package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单Id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 类型（0: 目录,1:菜单,2:按钮）
     */
    private Integer type;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 启用id默认1启用，2禁用
     */
    private Integer statu;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private Date createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private Date updatedate;

    /**console.log(menu);
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
    private String perms;
    private String component;
    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private List<SysMenu> children;

}
