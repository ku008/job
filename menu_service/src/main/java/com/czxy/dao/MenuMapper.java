package com.czxy.dao;

import com.czxy.domain.Menu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MenuMapper extends Mapper<Menu> {

    @Results({
            @Result(property = "mid",column = "mid"),
            @Result(property = "parentId",column = "parent_id"),
//            @Result(property = "mList",many = @Many(select = "com.czxy.dao.MenuMapper.findAll"),column = "mid")
    })
    @Select("select * from menu where parent_id = #{id}")
    List<Menu> find(@PathParam("id")Integer id);

}
