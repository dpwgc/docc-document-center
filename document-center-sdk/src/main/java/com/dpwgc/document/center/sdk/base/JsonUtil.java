package com.dpwgc.document.center.sdk.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String toJson(Object object) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        //将对象转为json字符串
        return jsonMapper.writeValueAsString(object);
    }
}
