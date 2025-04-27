package com.johnthan.weblogweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenIdConvertResult {
    @JsonProperty("ori_openid")
    private String oriOpenid;

    @JsonProperty("new_openid")
    private String newOpenid;

    @JsonProperty("err_msg")
    private String errMsg;

    // Getter和Setter方法
    public String getOriOpenid() {
        return oriOpenid;
    }

    public void setOriOpenid(String oriOpenid) {
        this.oriOpenid = oriOpenid;
    }

    public String getNewOpenid() {
        return newOpenid;
    }

    public void setNewOpenid(String newOpenid) {
        this.newOpenid = newOpenid;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}