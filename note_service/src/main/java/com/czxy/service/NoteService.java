package com.czxy.service;

import com.czxy.dao.NoteMapper;
import com.czxy.domain.Img;
import com.czxy.domain.Note;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class NoteService {
    @Resource
    private NoteMapper noteMapper;

    public BaseResult find(Integer uid){
        Example example = new Example(Note.class);
        example.createCriteria().andEqualTo("uid",uid);
        return new BaseResult(CommomUtil.SUCCESS,"查询简历成功...",noteMapper.selectByExample(example));
    }

    public void addNote(Note note) {
        note.setCreateTime(new Date());
        noteMapper.insert(note);
    }

    public void delNote(Integer nid) {
        noteMapper.deleteByPrimaryKey(nid);
    }

}
