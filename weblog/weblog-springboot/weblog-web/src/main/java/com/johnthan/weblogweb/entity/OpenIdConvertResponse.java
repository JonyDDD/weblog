package com.johnthan.weblogweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "返回值")
public class OpenIdConvertResponse {
    private Integer errcode;
    private String errmsg;

    @JsonProperty("result_list")
    private List<OpenIdConvertResult> resultList;

    // Getter和Setter方法
    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<OpenIdConvertResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<OpenIdConvertResult> resultList) {
        this.resultList = resultList;
    }
}