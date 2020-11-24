package com.czxy.feign;

import com.czxy.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "boss-service")
public interface BossFeign {

    @GetMapping("/boss/{bid}")
    ResponseEntity<BaseResult> findOne(@PathVariable Integer bid);

}
