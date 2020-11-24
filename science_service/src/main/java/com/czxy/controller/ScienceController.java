package com.czxy.controller;

import com.czxy.service.ScienceService;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/science")
public class ScienceController {
    @Resource
    private ScienceService scienceService;

    @GetMapping("/findAll")
    public ResponseEntity<BaseResult> findAll(){
        return ResponseEntity.ok(scienceService.findAll());
    }

}
