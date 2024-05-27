package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
public class BaseProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商公司名称
     */
    private String name;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 供应商类型
     */
    private Integer type;

    /**
     * 供应商名称
     */
    private String typeName;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;
    /**
     * 创建人名
     */
    private String createMan;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private Date createDate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 修改人
     */
    private String updateMan;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private Date updateDate;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;


}