package com.czxy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "`note`")
public class Note {
    @Id
    @Column(name = "nid")
    @GeneratedValue(generator = "JDBC")
    private Integer nid;

    /**
     * 简历名称
     */
    @Column(name = "note_name")
    private String noteName;

    /**
     * 简历地址
     */
    @Column(name = "note_address")
    private String noteAddress;

    /**
     * 上传时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 所属用户
     */
    @Column(name = "uid")
    private Integer uid;
}
