package com.czxy.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "boss")
public class Boss {
    @Id
    @Column(name = "bid")
    @GeneratedValue(generator = "JDBC")
    private Integer bid;

    /**
     * 负责人登录名
     */
    @Column(name = "bossname")
    private String bossname;

    /**
     * 负责人姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 登陆密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 头像
     */
    @Column(name = "picture")
    private String picture;

    /**
     * 负责人职位
     */
    @Column(name = "boss_pos")
    private String bossPos;

    /**
     * 所属公司
     */
    @Column(name = "com_id")
    private Integer comId;

    @Transient
    private Object company;

}
