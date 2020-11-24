package com.czxy.controller;

import com.czxy.service.RequireService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/require")
public class RequireController {
    @Resource
    private RequireService requireService;

    @GetMapping("/findByNum/{num}")
    public ResponseEntity<BaseResult> findByNum(@PathVariable Integer num){
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"查询要求...",requireService.findByNum(num)));
    }

    @GetMapping("/findOne/{rid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer rid) {
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"查询单个要求...",requireService.findOne(rid)));
    }

}
