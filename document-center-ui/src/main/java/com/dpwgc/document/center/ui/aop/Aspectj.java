package com.dpwgc.document.center.ui.aop;

import com.dpwgc.document.center.infrastructure.util.ExceptionUtil;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.ApiLog;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 切面
 * 权限校验
 * 错误抓取
 */
@Aspect
@Component
public class Aspectj {

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.dpwgc.document.center.ui.controller.*Controller.*(..))")
    public void aspect() {
    }

    /**
     * 切面处理
     */
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        // 获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //权限校验
        if(!ApiAuth.check(request.getHeader("Access-Token"))){
            return ResultDTO.getFailureResult("权限不足，无法访问").setCode(440);
        }

        //请求参数校验
        Object resultCheck = ApiGrab.resultCheckFail(joinPoint);
        if(resultCheck != null) {
            return resultCheck;
        }

        //uri获取
        String uri = request.getRequestURI();

        try {
            //执行请求
            Object result = joinPoint.proceed();

            //接口请求日志
            ApiLog apiLog = ApiLog.builder()
                    //请求路径
                    .url(request.getRequestURL().toString())
                    //处理的类
                    .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()))
                    //处理的方法类型
                    .httpMethod(request.getMethod())
                    //耗时
                    .timeCost(System.currentTimeMillis() - startTime)
                    //请求参数
                    .req(JsonUtil.toJson(ApiGrab.getRequest(joinPoint)))
                    //响应参数
                    .resp(JsonUtil.toJson(result))
                    //构建
                    .build();
            LogUtil.info(String.format("[%s] %s", request.getMethod(), uri), JsonUtil.toJson(apiLog), uri);

            //返回
            return result;

        } catch (Exception e) {
            LogUtil.error(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()), ExceptionUtil.GetStackTrace(e),uri);
            return ResultDTO.getFailureResult(e.getMessage()).setError(e.getMessage());
        }
    }
}
