package com.czxy.feign;

import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "company-service")
public interface CompanyFeign {

    @GetMapping("/company/{cid}")
    ResponseEntity<BaseResult> findOne(@PathVariable Integer cid);

}
