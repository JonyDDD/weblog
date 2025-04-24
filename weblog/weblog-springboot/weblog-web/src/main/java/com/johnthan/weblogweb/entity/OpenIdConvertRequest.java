package com.johnthan.weblogweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenIdConvertRequest {
    @JsonProperty("from_appid")
    private String fromAppid;

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