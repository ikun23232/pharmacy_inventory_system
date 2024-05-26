package com.kgc.aspect;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.kgc.annotation.Log;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.SysLogManage;
import com.kgc.entity.SysUser;
import com.kgc.service.SysLogManageService;
import com.kgc.utils.SaTokenUtil;
import com.kgc.utils.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author Tellsea
 * @date 2022/12/15
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@ConditionalOnProperty(prefix = CommonConst.PREFIX, name = "logAspect", havingValue = "true")
public class LogAspect {

    private final SysLogManageService sysOperationLogService;
    private final SysUserMapper sysUserMapper;

    @Pointcut("@annotation(com.kgc.annotation.Log)")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken,null);
        Object result = null;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        Log log = method.getAnnotation(Log.class);
        SysLogManage operationLog = new SysLogManage();
        if (SaTokenUtil.isLogin()) {
            operationLog.setUserId(loginUser.getUserid());
            operationLog.setUsername(loginUser.getUsername());
        }
        operationLog.setIp(ServletUtil.getClientIP(SpringUtil.getRequest()));
        operationLog.setTitle(log.value());
        operationLog.setUsageTime(stopWatch.getTotalTimeMillis());
        operationLog.setRequestUrl(SpringUtil.getRequest().getRequestURI());
        operationLog.setRequestMethod(method.getDeclaringClass().getName() + "." + method.getName() + "()");
        operationLog.setRequestParams(JSON.toJSONString(getFieldsName(proceedingJoinPoint)));
        operationLog.setResponseResult(JSON.toJSONString(result));
        operationLog.setOperationTime(new Date());
        sysOperationLogService.save(operationLog);
        return result;
    }

    /**
     * 获取参数列表
     *
     * @param proceedingJoinPoint
     * @return
     */
    public static Map<String, Object> getFieldsName(ProceedingJoinPoint proceedingJoinPoint) {
        // 参数值
        Object[] args = proceedingJoinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
