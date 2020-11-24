package com.czxy.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "cid")
    @GeneratedValue(generator = "JDBC")
    private Integer cid;

    /**
     * 公司全称
     */
    @Column(name = "com_name")
    private String comName;

    /**
     * 公司简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 公司简介
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 企业法人
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 公司地址
     */
    @Column(name = "com_address")
    private String comAddress;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 注册资本
     */
    @Column(name = "create_money")
    private Double createMoney;

    /**
     * 公司照片
     */
    @Column(name = "picture")
    private String picture;

    /**
     * 融资情况
     */
    @Column(name = "mon_require_id")
    private Integer monRequireId;

    @Transient
    private Object monRequire;

    /**
     * 公司规模
     */
    @Column(name = "scale_require_id")
    private Integer scaleRequireId;

    @Transient
    private Object scaleRequire;

    /**
     * 招聘负责人
     */
    @Column(name = "boss_id")
    private Integer bossId;

    @Transient
    private Object boss;
}
