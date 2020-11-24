package com.czxy.service;

import com.czxy.dao.BossMapper;
import com.czxy.domain.Boss;
import com.czxy.feign.CompanyFeign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BossService {
    @Resource
    private BossMapper bossMapper;
    @Resource
    private CompanyFeign companyFeign;

    public Boss login(Boss boss) {
        Example example = new Example(Boss.class);
        example.createCriteria().andEqualTo("bossname", boss.getBossname());
        List<Boss> list = bossMapper.selectByExample(example);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Boss register(Boss boss) {
        Example example = new Example(Boss.class);
        example.createCriteria().andEqualTo("bossname", boss.getBossname());
        List<Boss> list = bossMapper.selectByExample(example);
        for (Boss thisUser : list) {
            if (thisUser.getBossname().equals(boss.getBossname())) {
                return null;
            }
        }
        bossMapper.insert(boss);
        return boss;
    }

    public Boss findOne(Integer bid){
        return bossMapper.selectByPrimaryKey(bid);
    }

    public Boss findByBid(Integer bid) {
        Boss boss = bossMapper.selectByPrimaryKey(bid);
        boss.setCompany(companyFeign.findOne(boss.getComId()).getBody().getData());
        return boss;
    }

}
