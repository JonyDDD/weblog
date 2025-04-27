package com.johnthan.weblogweb.controller;

import com.johnthan.weblogweb.entity.OpenIdConvertRequest;
import com.johnthan.weblogweb.entity.OpenIdConvertResponse;
import com.johnthan.weblogweb.entity.OpenIdConvertResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/cgi-bin")
@Api(tags = "模拟微信")
public class WechatOpenIdController {

    @PostMapping("/changeopenid")
    @ApiOperation(value = "微信迁移接口")
    public ResponseEntity<OpenIdConvertResponse> changeOpenId(
            @RequestParam("access_token") String accessToken,
            @RequestBody OpenIdConvertRequest request) {

        // 验证access_token
        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(errorResponse(40001, "invalid credential"));
        }

        // 验证openid列表是否为空
        if (request.getOpenidList() == null || request.getOpenidList().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(errorResponse(63179, "openid_list is empty"));
        }

        // 验证openid列表数量是否超过限制
        if (request.getOpenidList().size() > 100) {
            return ResponseEntity.badRequest()
                    .body(errorResponse(63180, "too many openids, maximum is 100"));
        }

        // 处理openid列表
        List<OpenIdConvertResult> results = new ArrayList<>();
        Random random = new Random(); // 用于随机决定是否生成new_openid

        for (String openid : request.getOpenidList()) {
            OpenIdConvertResult result = new OpenIdConvertResult();
            result.setOriOpenid(openid);

            // 随机决定是否生成新的openid (约90%概率成功)
            if (random.nextDouble() < 0.9) {
                result.setNewOpenid(generateNewOpenId());
                result.setErrMsg("ok");
            } else {
                // 10%概率不生成new_openid
                result.setErrMsg("ori_openid error");
            }

            results.add(result);
        }

        // 构建响应
        OpenIdConvertResponse response = new OpenIdConvertResponse();
        response.setErrcode(0);
        response.setErrmsg("ok");
        response.setResultList(results);

        return ResponseEntity.ok(response);
    }

    private OpenIdConvertResponse errorResponse(int errcode, String errmsg) {
        OpenIdConvertResponse response = new OpenIdConvertResponse();
        response.setErrcode(errcode);
        response.setErrmsg(errmsg);
        return response;
    }

    private String generateNewOpenId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder("o");

        // 生成第一部分
        for (int i = 0; i < 15; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }

        sb.append("-");

        // 生成第二部分
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }

        return sb.toString();
    }

    private boolean isValidOpenIdFormat(String openid) {
        // 简单验证openid格式
        return openid != null && openid.startsWith("o") && openid.length() >= 15 && openid.length() <= 32;
    }
}