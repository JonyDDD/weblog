package com.johnthan.weblogweb.demos.web;

import com.johnthan.module.common.aspect.ApiOperationLog;
import com.johnthan.module.common.enums.ResponseCodeEnum;
import com.johnthan.module.common.exception.BizException;
import com.johnthan.module.common.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

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
