package com.dpwgc.document.center.ui.aop;

import com.dpwgc.document.center.infrastructure.util.ExceptionUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

        // 获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //权限校验
        if(!Auth.check(request.getHeader("Access-Token"))){
            return ResultDTO.getFailureResult("权限不足，无法访问");
        }

        //uri获取
        String uri = request.getRequestURI();

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            LogUtil.error(String.format("%s.%s error", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()), ExceptionUtil.GetStackTrace(e),uri);
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }
}
