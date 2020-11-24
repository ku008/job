package com.czxy.service;

import com.czxy.dao.MenuMapper;
import com.czxy.domain.Menu;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MenuService {
    @Resource
    private MenuMapper menuMapper;

    public BaseResult find(Integer mid) {
        return new BaseResult(CommomUtil.SUCCESS,"查询菜单成功...",menuMapper.find(mid));
    }

    public Menu findOne(Integer mid) {
        return menuMapper.selectByPrimaryKey(mid);
    }

}
