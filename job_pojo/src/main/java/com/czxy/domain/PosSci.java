package com.czxy.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "`pos_sci`")
public class PosSci {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 职位id
     */
    @Column(name = "pos_id")
    private Integer posId;

    /**
     * 技术id
     */
    @Column(name = "sci_id")
    private Integer sciId;

}
