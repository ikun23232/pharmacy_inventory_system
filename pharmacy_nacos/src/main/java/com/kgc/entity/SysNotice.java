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
    private String noticetitle;

    /**
     * 公告内容
     */
    @TableField("noticeContent")
    private String noticecontent;

    /**
     * 发布人
     */
    @TableField("userId")
    private Integer userid;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDateTime updatedate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;


}
