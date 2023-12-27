package com.bpmn.bpmnserver.controller;

import com.bpmn.bpmnserver.common.UserBpmn;
import com.bpmn.bpmnserver.result.Result;
import com.bpmn.bpmnserver.service.UserBpmnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户流程管理")
@RequestMapping("/userBpmn")
@RestController
@CrossOrigin("*")
public class UserBpmnController {


    @Autowired
    private Result myResult;

    @Autowired
    public UserBpmnService userBpmnService;



    @ApiOperation("获取全部用户流程信息----queryAllUserBpmn")
    @GetMapping("/queryAllUserBpmn")
    public Result<List<UserBpmn>> queryAllUserBpmn() {
        return myResult.setOk(userBpmnService.queryAllUserBpmn());

    }

    @ApiOperation("根据用户id获取用户流程信息----queryUserBpmnByUserId")
    @GetMapping("/queryUserBpmnByUserId/{userId}")
    public Result<List<UserBpmn>> queryUserBpmnByUserId(@PathVariable("userId") String userId) {
        return myResult.setOk(userBpmnService.queryUserBpmnByUserId(userId));
    }

    @ApiOperation("插入一条数据----save")
    @PostMapping("/save")
    public Result<Boolean> save(UserBpmn userBpmn) {
        String bpmnId = userBpmn.getBpmnId();
        String userId = userBpmn.getUserId();
        List<UserBpmn> list = userBpmnService.queryUserBpmnByUserId(userId);
        if(list != null){
            for(UserBpmn u : list){
                String sBpmnId = u.getBpmnId();
                if(sBpmnId.equals(bpmnId)){
                    return myResult.setOk(false);
                }
            }
        }
        return myResult.setOk(userBpmnService.insertOne(userBpmn));
    }

}









