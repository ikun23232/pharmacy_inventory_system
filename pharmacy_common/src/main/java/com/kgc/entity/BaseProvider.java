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
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDateTime updatedate;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;


}
