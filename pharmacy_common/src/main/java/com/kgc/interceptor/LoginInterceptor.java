package com.kgc.interceptor;


import com.kgc.utils.DateUtil;
import com.kgc.utils.JwtUtil;
import com.kgc.utils.ReplayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

//@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ReplayUtil replayUtil;

    private static final String[] EXCLUDE_PATHS = {"/user/login"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        //排静态资源
        String requestURI = request.getRequestURI();
        for (String excludePath : EXCLUDE_PATHS) {
            if (requestURI.contains(excludePath) || HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
                return true;
            }
        }


        //重放验签
        boolean checkFalg = checkSign(request);
        if (!checkFalg){
            return false;
        }
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //从redis中获取相同的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken==null){
                //token已经失效了
                throw new RuntimeException("token已经失效了");
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //放行
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }


    }
    /**
     * 验签是否通过
     * @param request
     * @return
     */
    private boolean checkSign(HttpServletRequest request) {
        String timestamp = request.getHeader("timestamp");
        String md5String = request.getHeader("signature");
        if (StringUtils.isEmpty(timestamp)) {
            logger.info("无时间戳");
            return false;
        }
        if (StringUtils.isEmpty(md5String)) {
            logger.info("无签名");
            return false;
        }
        //验签

        String randomTemp = replayUtil.checkRandom(md5String);
        if (randomTemp == null) {
            logger.info("验签不通过");
            return false;
        }
        boolean falg = DateUtil.checkReplaytimestamp(timestamp);
        if (!falg){
            logger.info("时间戳不通过");
            return false;
        }
        //验签通过删uuid
        replayUtil.removeRandom(md5String);
        return true;
    }
}
