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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
      @TableId(value = "userId", type = IdType.AUTO)
    private Integer userid;

    /**
     * 账户名
     */
    @TableField("userName")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄 1:男 0:女
     */
    private Integer age;

    /**
     * 性别，1：男 2：女
     */
    private Integer six;
    /**
     * 邮箱
     */
    private Integer email;

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
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;

    @TableField("isState")
    private Integer isstate;

    /**
     * 用户名
     */
    @TableField("nickName")
    private String nickname;


}
