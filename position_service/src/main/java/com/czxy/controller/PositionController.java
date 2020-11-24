package com.czxy.controller;

import com.czxy.domain.Position;
import com.czxy.service.PositionService;
import com.czxy.util.CommomUtil;
import com.czxy.vo.BaseResult;
import com.czxy.vo.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Resource
    private PositionService positionService;

    @GetMapping
    public ResponseEntity<BaseResult> find(PageRequest pageRequest) {
        BaseResult br = positionService.find(pageRequest);
        return ResponseEntity.ok(br);
    }

    @GetMapping("/findOne/{pid}")
    public ResponseEntity<BaseResult> findOne(@PathVariable Integer pid){
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"查询单个职位...",positionService.findOne(pid)));
    }

    //根据公司(code:1)/招聘者(code:2)查询职位
    @GetMapping("/findById/{code}/{id}")
    public ResponseEntity<BaseResult> findById(@PathVariable Integer code,@PathVariable Integer id){
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"根据公司/招聘者查询职位...",positionService.findById(code,id)));
    }

    @PostMapping
    public ResponseEntity<BaseResult> addPosition(@RequestBody Position position){
        positionService.addPosition(position);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"添加职位..."));
    }

    @PutMapping
    public ResponseEntity<BaseResult> updatePosition(@RequestBody Position position){
        positionService.updatePosition(position);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"修改职位..."));
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<BaseResult> delPosition(@PathVariable Integer pid){
        positionService.delPosition(pid);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"删除职位..."));
    }

    @DeleteMapping("/delmany/{ids}")
    public ResponseEntity<BaseResult> delmanyPosition(@PathVariable Integer[] ids){
        positionService.delmanyPosition(ids);
        return ResponseEntity.ok(new BaseResult(CommomUtil.SUCCESS,"批量删除职位..."));
    }

}
