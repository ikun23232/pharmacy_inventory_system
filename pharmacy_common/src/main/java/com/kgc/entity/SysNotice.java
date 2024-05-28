package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class SysNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
      @TableId(value = "noticeId", type = IdType.AUTO)
    private Integer noticeid;

    /**
     * 公告标题
     */
    @TableField("noticeTitle")
    @ExcelFiled("公告标题")
    private String noticetitle;

    /**
     * 公告内容
     */
    @TableField("noticeContent")
    @ExcelFiled("公告内容")
    private String noticecontent;

    /**
     * 发布人
     */

    /**
     * 创造时间
     */
    @TableField("createDate")
    @ExcelFiled("创造时间")
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
    @ExcelFiled("修改时间")
    private Date updatedate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;


}
