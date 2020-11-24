package com.czxy.domain;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "`position`")
public class Position {
    @Id
    @Column(name = "pid")
    @GeneratedValue(generator = "JDBC")
    private Integer pid;

    /**
     * 职位名称
     */
    @Column(name = "pos_name")
    private String posName;

    /**
     * 大概薪资(单位:K)
     */
    @Column(name = "salary")
    private String salary;

    /**
     * 职位详情
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 所属职位菜单
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 所属职位菜单
     */
    @Column(name = "exp_require_id")
    private Integer expRequireId;

    @Transient
    private Object expRequire;

    /**
     * 所属职位菜单
     */
    @Column(name = "edu_require_id")
    private Integer eduRequireId;

    @Transient
    private Object eduRequire;

    /**
     * 所属公司
     */
    @Column(name = "boss_id")
    private Integer bossId;

    @Transient
    private Object boss;

    /**
     * 所属公司
     */
    @Column(name = "com_id")
    private Integer comId;

    @Transient
    private Object company;

    @Transient
    private Integer[] sciIds;

    @Transient
    private List<Science> sciList;

}
