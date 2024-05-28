package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private Date createdate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private Date updatedate;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;


    private Integer parentId;
    /**
     * 下级分类
     */
    @TableField(exist = false)
    private List<BaseMedicineCategory> children;
    @TableField(exist = false)
    private String createUser;
    @TableField(exist = false)
    private List<BaseMedicine> Medicine;


}
