package com.czxy.controller;

import com.czxy.domain.Img;
import com.czxy.domain.Note;
import com.czxy.service.NoteService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Resource
    private NoteService noteService;

    @GetMapping("/{uid}")
    public ResponseEntity<BaseResult> find(@PathVariable Integer uid) {
        return ResponseEntity.ok(noteService.find(uid));
    }

    //添加上传的文件地址到数据库
    @PostMapping
    public ResponseEntity<BaseResult> addNote(@RequestBody Note note) {
        if (note != null) {
            noteService.addNote(note);
            return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "添加简历成功..."));
        }
        return ResponseEntity.ok(new BaseResult(CommomUtil.FAIL, "上传简历为空..."));
    }

    //从数据库删除上传的文件地址
    @DeleteMapping("/{nid}")
    public ResponseEntity<BaseResult> delNote(@PathVariable Integer nid) {
        noteService.delNote(nid);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "删除简历成功..."));
    }

}
