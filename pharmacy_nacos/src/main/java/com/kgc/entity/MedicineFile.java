package com.kgc.entity;

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
public class MedicineFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    private String id;

    /**
     * 文件路径
     */
    @TableField("filePath")
    private String filepath;

    /**
     * 药品id
     */
    private Integer medicineId;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private String isdel;


}
