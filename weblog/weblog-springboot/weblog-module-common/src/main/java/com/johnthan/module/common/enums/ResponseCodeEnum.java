package com.johnthan.module.common.enums;

import com.johnthan.module.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    //通用异常状态码
    SYSTEM_ERROR("10000","出错啦,后台哥哥正在努力修复中..."),
    SYSTEM_RUN_ERROR("10001","运行时错误,正在修复中"),
    //业务异常状态码
    PRODUCT_NOT_FOUND("20000","该产品不存在(测试使用)"),
    PRODUCT_ALREADY_USED("20001","该产品已使用(测试使用)"),
    //参数异常状态码
    PARAM_NOT_VALID("30000", "参数错误"),;


    //异常码
    private String errorCode;
    //错误信息
    private  String errorMessage;
}



