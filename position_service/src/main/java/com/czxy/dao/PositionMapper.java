package com.czxy.dao;

import com.czxy.domain.Position;
import com.czxy.domain.Science;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface PositionMapper extends Mapper<Position> {

    @Results({
            @Result(property = "sciName",column = "sci_name")
    })
    @Select("select * from science where sid in (select sci_id from pos_sci where pos_id = #{pid})")
    List<Science> findSciList(Integer pid);

}
