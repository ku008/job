package com.czxy.service;

import com.czxy.dao.CompanyMapper;
import com.czxy.domain.Company;
import com.czxy.feign.BossFeign;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyService {
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private RequireFeign requireFeign;
    @Resource
    private BossFeign bossFeign;

    public void setInfo(Company company){
        company.setMonRequire(requireFeign.findOne(company.getMonRequireId()).getBody().getData());
        company.setScaleRequire(requireFeign.findOne(company.getScaleRequireId()).getBody().getData());

        company.setBoss(bossFeign.findOne(company.getBossId()).getBody().getData());
    }

    public BaseResult find(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(),pageRequest.getRows());
        Example example = new Example(Company.class);
        Example.Criteria criteria = example.createCriteria();
        if (pageRequest.getKey()!=null&&!"".equals(pageRequest.getKey())){
            criteria.andLike("comName","%"+pageRequest.getKey()+"%");
        }
        List<Company> list = companyMapper.selectByExample(example);
        for (Company thisCompany : list) {
            setInfo(thisCompany);
        }

        PageInfo<Company> pageInfo = new PageInfo<>(list);
        return new BaseResult(CommomUtil.SUCCESS,"查询公司...",pageInfo);
    }

    public BaseResult findAll() {
        return new BaseResult(CommomUtil.SUCCESS,"查询公司...",companyMapper.selectAll());
    }

    public Company findOne(Integer cid){
        Company company = companyMapper.selectByPrimaryKey(cid);
        setInfo(company);
        return company;
    }

    public Integer addCompany(Company company) {
        company.setCreateTime(new Date());
        companyMapper.insert(company);
        return company.getCid();
    }

}
