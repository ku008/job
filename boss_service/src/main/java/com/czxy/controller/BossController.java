package com.czxy.controller;

import com.czxy.domain.Boss;
import com.czxy.service.AliYunMessageService;
import com.czxy.service.BossService;
import com.czxy.util.CommomUtil;
import com.czxy.util.JWTUtil;
import com.czxy.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/boss")
public class BossController {
    @Resource
    private BossService bossService;
    @Resource
    private AliYunMessageService aliYunMessageService;

    // 招聘者登陆
    @PostMapping("/login")
    public ResponseEntity<BaseResult> login(@RequestBody Boss boss) {
        Boss loginBoss = bossService.login(boss);
        BaseResult br = null;
        if (loginBoss == null) {
            br = new BaseResult(CommomUtil.FAIL, "用户名不存在！");
        } else {
            if (loginBoss.getPassword().equals(boss.getPassword())) {
                String token = JWTUtil.createToken(null, loginBoss, "czgz", 30);
                br = new BaseResult(CommomUtil.SUCCESS, "登陆成功...", token);
            } else {
                br = new BaseResult(CommomUtil.FAIL, "密码错误！");
            }
        }
        return ResponseEntity.ok(br);
    }

    // 求职者注册
    @PostMapping("/register")
    public ResponseEntity<BaseResult> register(@RequestBody Boss boss) {
        Boss registerBoss = bossService.register(boss);
        if (registerBoss == null) {
            return ResponseEntity.ok(new BaseResult(CommomUtil.FAIL, "用户名重复，换一个试试..."));
        }
        String token = JWTUtil.createToken(null, registerBoss, "czgz", 30);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "注册成功...", token));
    }

    @GetMapping("/{bid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer bid) {
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "查询单个招聘负责人...", bossService.findOne(bid)));
    }

    @GetMapping("/findByBid/{bid}")
    public ResponseEntity<BaseResult> findByBid(@PathVariable Integer bid) {
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "查询单个招聘负责人...", bossService.findByBid(bid)));
    }

    /**
     * 使用阿里云发送手机验证码功能
     */
    @GetMapping(value = "/getVerify/{phoneNumber}")
    public ResponseEntity<BaseResult> sendMessageToIphone(@PathVariable String phoneNumber) {
        //调用接口，发短信
        BaseResult br = aliYunMessageService.sendMessage(phoneNumber);
        System.out.println(br);
        return ResponseEntity.ok(br);
    }

}
