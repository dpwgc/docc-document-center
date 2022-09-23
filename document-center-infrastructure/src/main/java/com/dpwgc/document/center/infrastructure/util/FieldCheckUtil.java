package com.dpwgc.document.center.infrastructure.util;

import org.springframework.validation.BindingResult;

/**
 * 接口字段校验
 */
public class FieldCheckUtil {

    public static String check(BindingResult bindingResult) {
        // 参数校验
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError() != null) {
                //错误，返回错误信息
                return bindingResult.getFieldError().getDefaultMessage();
            }
        }
        //正确
        return null;
    }
}
