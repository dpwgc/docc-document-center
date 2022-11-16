package com.dpwgc.document.center.sdk.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiLog {

    // ip
    private String ip;

    // url
    private String url;

    // 请求方式 GET POST
    private String httpMethod;

    // 类方法
    private String classMethod;

    //请求参数
    private String req;

    //响应参数
    private String resp;

    // 接口耗时
    private Long timeCost;
}
