package com.czxy.service;

import com.czxy.dao.UserMapper;
import com.czxy.domain.Img;
import com.czxy.domain.User;
import com.czxy.feign.RequireFeign;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import com.czxy.vo.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.smartcardio.CommandAPDU;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RequireFeign requireFeign;

    public User login(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        List<User> list = userMapper.selectByExample(example);
        return list.size() > 0 ? list.get(0) : null;
    }

    public User register(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        List<User> list = userMapper.selectByExample(example);
        for (User thisUser : list) {
            if (thisUser.getUsername().equals(user.getUsername())) {
                return null;
            }
        }
        userMapper.insert(user);
        return user;
    }

    public User findOne(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (user.getStateRequireId() != null) {
            user.setStateRequire(requireFeign.findOne(user.getStateRequireId()).getBody().getData());
        }
        return user;
    }

    public User editUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getUid());
    }

    public BaseResult find(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getRows());
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (pageRequest.getEduId() != null && pageRequest.getEduId() > 0) {
            criteria.andEqualTo("eduRequireId", pageRequest.getEduId());
        }
        if (pageRequest.getKey() != null && !"".equals(pageRequest.getKey())) {
            criteria.andLike("name", "%" + pageRequest.getKey() + "%");
        }
        List<User> list = userMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new BaseResult(CommomUtil.SUCCESS, "查询用户...", pageInfo);
    }

    public User forgetLogin(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        User forgetUser = userMapper.selectOneByExample(example);
        if (forgetUser != null) {
            user.setUid(forgetUser.getUid());
            userMapper.updateByPrimaryKeySelective(user);
            return userMapper.selectByPrimaryKey(forgetUser);
        } else {
            return null;
        }
    }

    public List<User> findTalkUser(Integer bid) {
        List<User> list = new ArrayList<>();
        for (Integer thisUid : userMapper.findTalkUser(bid)) {
            list.add(userMapper.selectByPrimaryKey(thisUid));
        }
        return list;
    }

}
