package com.kgc.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.kgc.annotation.LoginLog;
import com.kgc.entity.SysLoginLog;
import com.kgc.entity.SysUser;
import com.kgc.service.SysLoginLogService;
import com.kgc.utils.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 登录日志切面
 *
 * @author Tellsea
 * @date 2022/12/15
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ConditionalOnProperty(prefix = CommonConst.PREFIX, name = "loginLogAspect", havingValue = "true")
public class LoginLogAspect {

    private final SysLoginLogService sysLoginLogService;

    @Pointcut("@annotation(com.kgc.annotation.LoginLog)")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        LoginLog loginLog = method.getAnnotation(LoginLog.class);
        SysUser loginBodyVo = JSON.parseObject(JSON.toJSONString(proceedingJoinPoint.getArgs()[0]), SysUser.class);
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserName(loginBodyVo.getUsername());
        sysLoginLog.setOperationIp(ServletUtil.getClientIP(SpringUtil.getRequest()));
        sysLoginLog.setUsageTime(stopWatch.getTotalTimeMillis());
        sysLoginLog.setResponseResult(JSON.toJSONString(result));
        sysLoginLog.setOperationTime(new Date());
        sysLoginLogService.save(sysLoginLog);
        return result;
    }


}
