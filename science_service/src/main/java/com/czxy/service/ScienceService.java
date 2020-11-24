package com.czxy.service;

import com.czxy.dao.ScienceMapper;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ScienceService {
    @Resource
    private ScienceMapper scienceMapper;

    public BaseResult findAll(){
        return new BaseResult(CommomUtil.SUCCESS,"查询所有技术标签...",scienceMapper.selectAll());
    }

}
