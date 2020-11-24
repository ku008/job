package com.czxy.controller;

import com.czxy.service.MenuService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/{mid}")
    public ResponseEntity<BaseResult> find(@PathVariable Integer mid){
        BaseResult br = menuService.find(mid);
        return ResponseEntity.ok(br);
    }

    @GetMapping("/findOne/{mid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer mid){
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"查询单个菜单...",menuService.findOne(mid)));
    }

}
