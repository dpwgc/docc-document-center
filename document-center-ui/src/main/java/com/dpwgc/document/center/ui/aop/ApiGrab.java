package com.dpwgc.document.center.ui.aop;

import com.dpwgc.document.center.infrastructure.util.FieldCheckUtil;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * 在切面获取http请求中的参数
 */
public class ApiGrab {

    /**
     * 校验请求参数
     */
    public static Object resultCheckFail(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            //如果是参数校验对象
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                // 参数校验
                String checkRes = FieldCheckUtil.check(bindingResult);
                if (checkRes != null) {
                    return ResultDTO.getFailureResult(checkRes);
                }
                break;
            }
        }
        return null;
    }

    /**
     * 获取请求参数
     */
    public static Object getRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile) {
                //ServletRequest，ServletResponse,MultipartFile不能序列化
                continue;
            }
            //如果是参数校验对象
            if (arg instanceof BindingResult) {
                //跳过
                continue;
            }
            if (arg != null) {
                try {
                    return arg;
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }
}
