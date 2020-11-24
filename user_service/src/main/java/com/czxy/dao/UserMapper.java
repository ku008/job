package com.czxy.dao;

import com.czxy.domain.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Select("select distinct uid from talk where bid = #{bid}")
    List<Integer> findTalkUser(Integer bid);

}
