package com.johnthan.weblogweb.controller;

import com.johnthan.module.common.aspect.ApiOperationLog;
import com.johnthan.module.common.enums.ResponseCodeEnum;
import com.johnthan.module.common.exception.BizException;
import com.johnthan.module.common.utils.JsonUtil;
import com.johnthan.module.common.utils.Response;
import com.johnthan.weblogweb.demos.web.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/newTime")
    @ApiOperation(value =  "测试时间接口")
    @ApiOperationLog(description = "测试时间")
    public Response TestNewTime(@RequestBody @Validated User user){
        log.info("RequestBody:{}", JsonUtil.toJsonString(user));

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
    }


    @PostMapping("/testUser")
    @ApiOperationLog(description = "测试用户参数")
    public ResponseEntity<String> testUser(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 是否存在校验错误
        if (bindingResult.hasErrors()) {
            // 获取校验不通过字段的提示信息
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMsg);
        }
        // 返参
        return  ResponseEntity.ok().body("成功");
    }

    @PostMapping("/testValidateUser")
    @ApiOperationLog(description = "测试用户参数")
    public Response<User> testUserParams(@RequestBody @Validated User user) {
        return  Response.success(user);
    }

    @PostMapping("/testGlobalError")
    @ApiOperationLog(description = "测试系统异常")
    public Response<BindingResult> test(@RequestBody @Validated User user , BindingResult bindingResult){
        throw new BizException(ResponseCodeEnum.SYSTEM_ERROR);
    }

    @PostMapping("/testRunTimeError")
    @ApiOperationLog(description = "测试接口")
    public Response testRunTimeError(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 主动定义一个运行时异常，分母不能为零
        int i = 1 / 0;
        return Response.success();
    }

    @PostMapping("/testSuccess")
    @ApiOperationLog(description = "测试成功")
    public Response<String> testSuccessRequest(){
        return  Response.success("请求成功");
    }
}
