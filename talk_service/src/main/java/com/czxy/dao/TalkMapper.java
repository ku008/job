package com.czxy.dao;

import com.czxy.domain.Talk;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface TalkMapper extends Mapper<Talk> {

    @Results(
            @Result(property = "talkTime", column = "talk_time")
    )
    @Select("select * from talk where uid = #{uid} and bid = #{bid} order by talk_time")
    List<Talk> find(Integer uid, Integer bid);

}
