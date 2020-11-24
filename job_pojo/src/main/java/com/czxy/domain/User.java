package com.czxy.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "`uid`")
    @GeneratedValue(generator = "JDBC")
    private Integer uid;

    /**
     * 登录名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 微信(选填)
     */
    @Column(name = "wechat")
    private String wechat;

    /**
     * 邮箱(选填)
     */
    @Column(name = "email")
    private String email;

    /**
     * 头像
     */
    @Column(name = "picture")
    private String picture;

    /**
     * 经验要求
     */
    @Column(name = "exp_require_id")
    private Integer expRequireId;

    @Transient
    private Object expRequire;

    /**
     * 学历要求
     */
    @Column(name = "edu_require_id")
    private Integer eduRequireId;

    @Transient
    private Object eduRequire;

    /**
     * 在/离职状态
     */
    @Column(name = "state_require_id")
    private Integer stateRequireId;

    @Transient
    private Object stateRequire;

}
