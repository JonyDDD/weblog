package com.johnthan.module.common.exception;


import com.johnthan.module.common.enums.ResponseCodeEnum;
import com.johnthan.module.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    /**
     * 捕获自定义业务异常
    * @return
    */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request,BizException e){
        log.warn("{} request fail , errorCode: {} , errorMessage: {} ",request.getRequestURI(),e.getErrorCode(),e.getErrorMessage());
        return Response.fail(e);
    }

    /**
     * 其他类型异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public  Response<Object> handleOtherException(HttpServletRequest request , Exception e){
      log.error("{} request error,", request.getRequestURI(),e);
      return  Response.fail(ResponseCodeEnum.SYSTEM_RUN_ERROR);
    }

    /**
     * 参数异常
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public  Response<Object> handelMethodArgumentNotValidException(HttpServletRequest request , MethodArgumentNotValidException e){
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode();

        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();


        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors->{
            errors.forEach(error->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("'; "));
        });

        String errMsg = sb.toString();

        log.warn("{} request error, errorCode: {} , errorMessage: {}", request.getRequestURI(), errorCode,errMsg);
            return  Response.fail(errorCode,errMsg);
    }



}
