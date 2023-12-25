package com.bpmn.bpmnserver.controller;

import com.bpmn.bpmnserver.common.User;
import com.bpmn.bpmnserver.result.Result;
import com.bpmn.bpmnserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RequestMapping("/user")
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private Result myResult;

    @Autowired
    private UserService userService;


    @ApiOperation("获取全部用户信息----queryAllUser")
    @GetMapping("/queryAllUser")
    public Result<List<User>> queryAllUser() {
        return myResult.setOk(userService.queryAllUser());
    }

    @ApiOperation("根据用户id获取用户信息----queryUserById")
    @GetMapping("/queryUserById/{userId}")
    public Result<User> queryUserById(@PathVariable("userId") String userId) {
        return myResult.setOk(userService.queryUserById(userId));
    }

}
