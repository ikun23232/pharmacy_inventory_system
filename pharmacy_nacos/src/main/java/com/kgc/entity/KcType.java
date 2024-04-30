package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
public class KcType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出入库类型id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 出入库类型
     */
    private String type;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private String createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDateTime updatedate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private String updateby;

    /**
     * 删除id
     */
    @TableField("isDel")
    private String isdel;


}
