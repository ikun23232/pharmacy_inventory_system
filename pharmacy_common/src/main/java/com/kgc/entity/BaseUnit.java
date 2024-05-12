package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 计量单位id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计量单位名称
     */
    private String name;

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
    private String updatetime;

    /**
     * 符号
     */
    private String sign;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;


}
