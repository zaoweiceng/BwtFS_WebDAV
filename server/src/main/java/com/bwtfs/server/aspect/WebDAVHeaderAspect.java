package com.bwtfs.server.aspect;

import com.bwtfs.pojo.entity.Result;
import com.bwtfs.server.annotation.LockMapping;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
@Slf4j
@SuppressAjWarnings
public class WebDAVHeaderAspect {
    @Pointcut("@annotation(com.bwtfs.server.annotation.WebDev)")
    public void pt() {}

    @Around("pt()")
    public Object aroundDownload(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return joinPoint.proceed();
        }
        // 确保第一个参数是 HttpServletRequest 类型，用于获取请求体的HTTP请求参数，然后进行对比校验
        Object firstArg = args[0];
        if (!(firstArg instanceof HttpServletRequest)) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        String method = request.getMethod();
        Annotation[] annotations = joinPoint
                .getSignature()
                .getDeclaringType()
                .getMethod(joinPoint.getSignature().getName(), new Class[]{HttpServletRequest.class})
                .getAnnotations();
//        Annotation[] annotations = joinPoint.getSignature().getDeclaringType().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof LockMapping lockMapping) {
                String[] M = lockMapping.M();
                log.info("LockMapping注解的值: {}", M[0]);
                if (!M[0].equals(method)) {
                    log.error("HTTP方法错误: {}", method);
                    return Result.error("HTTP方法错误");
                }
            }
        }
        // 执行原方法
        return joinPoint.proceed();
    }
}
