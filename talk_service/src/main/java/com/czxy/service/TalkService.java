package com.czxy.service;

import com.czxy.dao.TalkMapper;
import com.czxy.domain.Talk;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class TalkService {
    @Resource
    private TalkMapper talkMapper;

    public BaseResult find(Talk talk) {
        return new BaseResult(CommomUtil.SUCCESS, "查询对话记录成功...", talkMapper.find(talk.getUid(), talk.getBid()));
    }

    public void addTalk(Talk talk) {
        talk.setTalkTime(new Date());
        talkMapper.insert(talk);
    }

    public void delTalk(Integer tid) {
        talkMapper.deleteByPrimaryKey(tid);
    }

}
