package com.czxy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`talk`")
public class Talk {
    @Id
    @Column(name = "tid")
    @GeneratedValue(generator = "JDBC")
    private Integer tid;

    /**
     * 沟通内容
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 沟通时间
     */
    @Column(name = "talk_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date talkTime;

    /**
     * 求职者(user),招聘者(boss)
     */
    @Column(name = "code")
    private String code;

    /**
     * 所属求职者
     */
    @Column(name = "uid")
    private Integer uid;

    /**
     * 所属招聘者
     */
    @Column(name = "bid")
    private Integer bid;

}
