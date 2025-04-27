package com.johnthan.weblogweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "请求体")
public class OpenIdConvertRequest {
    @JsonProperty("from_appid")
    @ApiModelProperty(value = "旧公众号appid")
    private String fromAppid;

    @ApiModelProperty(value = "旧用户openId列表")
    @JsonProperty("openid_list")
    private List<String> openidList;

    // Getter和Setter方法
    public String getFromAppid() {
        return fromAppid;
    }

    public void setFromAppid(String fromAppid) {
        this.fromAppid = fromAppid;
    }

    public List<String> getOpenidList() {
        return openidList;
    }

    public void setOpenidList(List<String> openidList) {
        this.openidList = openidList;
    }
}