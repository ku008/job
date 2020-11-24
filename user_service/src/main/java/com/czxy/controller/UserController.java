package com.czxy.controller;

import com.aliyun.oss.model.GetObjectRequest;
import com.czxy.domain.User;
import com.czxy.service.UserService;
import com.czxy.util.CommomUtil;
import com.czxy.util.JWTUtil;
import com.czxy.vo.BaseResult;
import com.czxy.vo.PageRequest;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import static com.czxy.util.QiniuUtil.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    // 求职者登陆
    @PostMapping("/login")
    public ResponseEntity<BaseResult> login(@RequestBody User user) {
        User loginUser = userService.login(user);
        BaseResult br = null;
        if (loginUser == null) {
            br = new BaseResult(CommomUtil.FAIL, "登陆名不存在！");
        } else {
            if (loginUser.getPassword().equals(user.getPassword())) {
                String token = JWTUtil.createToken(loginUser, null, "czgz", 30);
                br = new BaseResult(CommomUtil.SUCCESS, "登陆成功...", token);
            } else {
                br = new BaseResult(CommomUtil.FAIL, "密码错误！");
            }
        }
        return ResponseEntity.ok(br);
    }

    // 求职者注册
    @PostMapping("/register")
    public ResponseEntity<BaseResult> register(@RequestBody User user) {
        User registerUser = userService.register(user);
        if (registerUser == null) {
            return ResponseEntity.ok(new BaseResult(CommomUtil.FAIL, "用户名重复，换一个试试..."));
        }
        String token = JWTUtil.createToken(registerUser, null, "czgz", 30);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "注册成功...", token));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer uid) {
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "查询单个用户...", userService.findOne(uid)));
    }

    @PutMapping
    public ResponseEntity<BaseResult> editUser(@RequestBody User user) {
        User editUser = userService.editUser(user);
        String token = JWTUtil.createToken(editUser, null, "czgz", 30);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "修改用户...", token));
    }

    // 暂时未用到(查询用户列表)
    @GetMapping
    public ResponseEntity<BaseResult> find(PageRequest pageRequest) {
        BaseResult br = userService.find(pageRequest);
        return ResponseEntity.ok(br);
    }

    // 忘记密码
    @PostMapping("/forgetLogin")
    public ResponseEntity<BaseResult> forgetLogin(@RequestBody User user) {
        System.out.println("忘记用户：" + user);
        User forgetUser = userService.forgetLogin(user);
        System.out.println("忘记用户返回：" + forgetUser);
        if (forgetUser != null) {
            String token = JWTUtil.createToken(forgetUser, null, "czgz", 30);
            return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "新密码设置成功..", token));
        }
        return ResponseEntity.ok(new BaseResult(CommomUtil.FAIL, "登陆名不存在..."));
    }

    @GetMapping("/findTalkUser/{bid}")
    public ResponseEntity<BaseResult> findTalkUser(@PathVariable Integer bid) {
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "查询对话用户成功...", userService.findTalkUser(bid)));
    }

    @PostMapping("/upload")
    public ResponseEntity<BaseResult> upload(MultipartFile file) throws IOException {
        Configuration cfg = new Configuration(Zone.zone0());// 指定区域为：华东
        UploadManager uploadManager = new UploadManager(cfg);
        String uptoken = auth.uploadToken(bucket, "job/user/" + file.getOriginalFilename());

        if (file != null) {
            try {
                byte[] data = FileCopyUtils.copyToByteArray(file.getInputStream());
                Response response = uploadManager.put(data, "job/user/" + file.getOriginalFilename(), uptoken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS, "上传成功...", "http://q6v13pd6k.bkt.clouddn.com/" + putRet.key));
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println("异常：" + r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        }
        return ResponseEntity.ok(new BaseResult(CommomUtil.FAIL, "上传失败..."));
    }

}
