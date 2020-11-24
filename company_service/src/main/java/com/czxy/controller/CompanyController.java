package com.czxy.controller;

import com.czxy.domain.Company;
import com.czxy.service.CompanyService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import com.czxy.vo.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<BaseResult> find(PageRequest pageRequest){
        BaseResult br  = companyService.find(pageRequest);
        return ResponseEntity.ok(br);
    }

    @GetMapping("/findAll")
    public ResponseEntity<BaseResult> findAll(){
        BaseResult br  = companyService.findAll();
        return ResponseEntity.ok(br);
    }

    @GetMapping("/{cid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer cid){
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"查询单个公司...",companyService.findOne(cid)));
    }

    @PostMapping
    public ResponseEntity<BaseResult> addCompany(@RequestBody Company company){
        Integer cid = companyService.addCompany(company);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"添加公司...",cid));
    }

}
