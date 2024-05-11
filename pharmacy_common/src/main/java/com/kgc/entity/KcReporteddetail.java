package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**

 *
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KcReporteddetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报损明细Id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 药品Id
     */
    @TableField("medicineId")
    private Integer medicineId;

    /**
     * 报损订单Id
     */
    @TableField("reportedId")
    private Integer reportedId;

    /**
     * 报损订单号编号
     */
    @TableField("reportedCode")
    private String reportedCode;

    /**
     * 报损数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 报损人
     */
    @TableField("createdBy")
    private String createdBy;

    /**
     * 报损时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private LocalDateTime modificationTime;

    /**
     * 修改人
     */
    @TableField("modificationBy")
    private String modificationBy;

    /**
     * 删除
     */
    private String isDel;


}
