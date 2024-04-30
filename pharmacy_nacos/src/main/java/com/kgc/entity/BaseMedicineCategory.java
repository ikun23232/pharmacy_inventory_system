package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class BaseMedicineCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品种类id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医用商品类型
     */
    private String name;

    /**
     * 编码
     */
    private String encoding;

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
