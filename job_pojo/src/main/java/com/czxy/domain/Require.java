package com.czxy.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`require`")
public class Require {
    @Id
    @Column(name = "rid")
    @GeneratedValue(generator = "JDBC")
    private Integer rid;

    /**
     * 查询出不同的要求
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 具体要求
     */
    @Column(name = "req_name")
    private String reqName;
}