package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @TableField("账户")
    @ExcelFiled("商品主键")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄 1:男 0:女
     */
    @ExcelFiled("年龄")

    private Integer age;

    /**
     * 性别，1：男 2：女
     */
    private Integer sex;
    /**
     * 邮箱
     */
    @ExcelFiled("邮箱")
    private String email;

    /**
     * 创造时间
     */
    @TableField("createDate")
    @ExcelFiled("创建时间")
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

    @TableField("isState")
    private Integer isstate;

    /**
     * 用户名
     */
    @TableField("nickName")
    @ExcelFiled("用户名")
    private String nickname;

//    @TableField(exist = false)
//    private List<BaseMedicineCategory> children;


    @TableField(exist = false)
    private List<SysRole> sysRoles = new ArrayList<>();
}
