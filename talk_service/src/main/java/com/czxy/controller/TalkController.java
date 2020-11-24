package com.czxy.controller;

import com.czxy.domain.Talk;
import com.czxy.service.TalkService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/talk")
public class TalkController {
    @Resource
    private TalkService talkService;

    @GetMapping
    public ResponseEntity<BaseResult> find(Talk talk) {
        BaseResult br = talkService.find(talk);
        return ResponseEntity.ok(br);
    }

    @PostMapping
    public ResponseEntity<BaseResult> addTalk(@RequestBody Talk talk) {
        talkService.addTalk(talk);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "添加对话成功..."));
    }

    @DeleteMapping("/{tid}")
    public ResponseEntity<BaseResult> delTalk(@PathVariable Integer tid) {
        talkService.delTalk(tid);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "撤回对话成功..."));
    }

}
