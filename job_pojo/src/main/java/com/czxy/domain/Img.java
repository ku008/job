package com.czxy.domain;

import lombok.Data;

/**
 * 方便写进数据库
 */
@Data
public class Img {

    private Integer id;
    private String code;
    private String imgUrl;

}
