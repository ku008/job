package com.czxy.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "science")
public class Science {
    @Id
    @Column(name = "sid")
    @GeneratedValue(generator = "JDBC")
    private Integer sid;

    /**
     * 技术名称
     */
    @Column(name = "sci_name")
    private String sciName;
}