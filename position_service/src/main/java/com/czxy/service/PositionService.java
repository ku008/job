package com.czxy.service;

import com.czxy.dao.PosSciMapper;
import com.czxy.dao.PositionMapper;
import com.czxy.domain.PosSci;
import com.czxy.domain.Position;
import com.czxy.feign.BossFeign;
import com.czxy.feign.CompanyFeign;
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
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

@Service
@Transactional
public class PositionService {
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private RequireFeign requireFeign;
    @Resource
    private BossFeign bossFeign;
    @Resource
    private CompanyFeign companyFeign;
    @Resource
    private PosSciMapper posSciMapper;

    public void setInfo(Position position) {
        position.setExpRequire(requireFeign.findOne(position.getExpRequireId()).getBody().getData());
        position.setEduRequire(requireFeign.findOne(position.getEduRequireId()).getBody().getData());

        position.setBoss(bossFeign.findOne(position.getBossId()).getBody().getData());
        position.setCompany(companyFeign.findOne(position.getComId()).getBody().getData());
        position.setSciList(positionMapper.findSciList(position.getPid()));
    }

    public BaseResult find(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getRows());
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        if (pageRequest.getExpId() != null && pageRequest.getExpId() >= 0) {
            criteria.andEqualTo("expRequireId", pageRequest.getExpId());
        }
        if (pageRequest.getEduId() != null && pageRequest.getEduId() >= 0) {
            criteria.andEqualTo("eduRequireId", pageRequest.getEduId());
        }
        if (pageRequest.getMenuId() != null && pageRequest.getMenuId() >= 0) {
            criteria.andEqualTo("menuId", pageRequest.getMenuId());
        }
        if (pageRequest.getKey() != null && !"".equals(pageRequest.getKey())) {
            criteria.andLike("posName", "%" + pageRequest.getKey() + "%");
        }
        List<Position> list = positionMapper.selectByExample(example);
        for (Position thisPosition : list) {
            setInfo(thisPosition);
        }

        PageInfo<Position> pageInfo = new PageInfo<>(list);
        return new BaseResult(CommomUtil.SUCCESS, "查询职位...", pageInfo);
    }

    public Position findOne(Integer pid) {
        Position position = positionMapper.selectByPrimaryKey(pid);
        setInfo(position);

        Example example = new Example(PosSci.class);
        example.createCriteria().andEqualTo("posId", pid);
        List<PosSci> list = posSciMapper.selectByExample(example);
        Integer[] sciIds = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sciIds[i] = list.get(i).getSciId();
        }
        position.setSciIds(sciIds);
        return position;
    }

    public List<Position> findById(Integer code, Integer id) {
        Example example = new Example(Position.class);
        if (code == 1)
            example.createCriteria().andEqualTo("comId", id);
        else
            example.createCriteria().andEqualTo("bossId", id);
        List<Position> list = positionMapper.selectByExample(example);
        for (Position thisPosition : list) {
            setInfo(thisPosition);
        }
        return list;
    }

    public void addPosition(Position position) {
        int pid = positionMapper.insert(position);

        if (position.getSciIds() != null) {
            for (int i = 0; i < position.getSciIds().length; i++) {
                PosSci posSci = new PosSci();
                posSci.setPosId(pid);
                posSci.setSciId(position.getSciIds()[i]);
                posSciMapper.insert(posSci);
            }
        }
    }

    public void updatePosition(Position position) {
        positionMapper.updateByPrimaryKeySelective(position);
        Example example = new Example(PosSci.class);
        example.createCriteria().andEqualTo("posId", position.getPid());
        posSciMapper.deleteByExample(example);

        if (position.getSciIds() != null) {
            for (int i = 0; i < position.getSciIds().length; i++) {
                PosSci posSci = new PosSci();
                posSci.setPosId(position.getPid());
                posSci.setSciId(position.getSciIds()[i]);
                posSciMapper.insert(posSci);
            }
        }
    }

    public void delPosition(Integer pid) {
        Example example = new Example(PosSci.class);
        example.createCriteria().andEqualTo("posId", pid);
        posSciMapper.deleteByExample(example);
        positionMapper.deleteByPrimaryKey(pid);
    }

    public void delmanyPosition(Integer[] ids) {
        for (Integer thisPid : ids) {
            delPosition(thisPid);
        }
    }

}
