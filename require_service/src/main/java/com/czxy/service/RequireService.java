package com.czxy.service;

import com.czxy.dao.RequireMapper;
import com.czxy.domain.Require;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RequireService {
    @Resource
    private RequireMapper requireMapper;

    public List<Require> findByNum(Integer num) {
        Example example = new Example(Require.class);
        example.createCriteria().andEqualTo("num",num);
        return requireMapper.selectByExample(example);
    }

    public Require findOne(Integer rid){
        return requireMapper.selectByPrimaryKey(rid);
    }

}
