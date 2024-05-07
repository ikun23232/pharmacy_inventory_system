//package com.kgc.interceptor;
//
//
//import cn.dev33.satoken.stp.SaTokenInfo;
//import cn.dev33.satoken.stp.StpUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    private static final String[] EXCLUDE_PATHS = {"/user/login"};
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//
//        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//            System.out.println("OPTIONS请求，放行");
//            return true;
//        }
//        String requestURI = request.getRequestURI();
//        for (String excludePath : EXCLUDE_PATHS) {
//            if (requestURI.contains(excludePath) || HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//                return true;
//            }
//        }
//
//
//        //令牌验证
//        String token = request.getHeader("Authorization");
//        //验证token
//        try {
//            //从redis中获取相同的token
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            String redisToken = operations.get(token);
//            if (redisToken==null){
//                //token已经失效了
//                throw new RuntimeException("token已经失效了");
//            }
//            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//            //放行
//            return true;
//        } catch (Exception e) {
//            //http响应状态码为401
//            response.setStatus(401);
//            //不放行
//            return false;
//        }
//    }
//
//
//}
