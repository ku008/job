package com.czxy.domain;

import javax.persistence.*;
import lombok.Data;import java.util.List;

@Data
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "mid")
    @GeneratedValue(generator = "JDBC")
    private Integer mid;

    /**
     * 职位选择名
     */
    @Column(name = "mname")
    private String mname;

    /**
     * 所属职位菜单
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 子菜单集
     */
    @Transient
    private List<Menu> mList;
}
