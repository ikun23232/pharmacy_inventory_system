//package com.kgc.controller;
//
//import com.kgc.entity.Message;
//import com.kgc.utils.DateUtil;
//import com.kgc.utils.Md5Util;
//import com.kgc.utils.ReplayUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import sun.security.provider.MD5;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author: 欧洋宏
// * @create: 2024-05-07 09:13
// **/
//@RestController
//@RequestMapping("/system")
//public class SystemController {
//    private Logger logger=LoggerFactory.getLogger(getClass());
//    @Autowired
//    private ReplayUtil replayUtil;
//
//    @RequestMapping("/getUUID")
//    public String getUUID(){
//        replayUtil.checkRandom("");
//        String random = replayUtil.getRandom();
//        String md5String = Md5Util.getMD5String(random);
//        return md5String;
//    }
//    /**
//     * 验签是否通过
//     * @param request
//     * @return
//     */
//    private boolean CheckSign(HttpServletRequest request) {
//        String timestamp = request.getHeader("timestamp");
//        String random = request.getHeader("signature");
//        if (StringUtils.isEmpty(timestamp)) {
//            logger.info("无时间戳");
//            return false;
//        }
//        if (StringUtils.isEmpty(random)) {
//            logger.info("无签名");
//            return  false;
//        }
//        //验签
//
//        String randomTemp = replayUtil.checkRandom(random);
//        if (randomTemp == null) {
//            logger.info("验签不通过");
//            return false;
//        }
//        boolean falg = DateUtil.checkReplaytimestamp(timestamp);
//        if (!falg){
//            logger.info("时间戳不通过");
//            return false;
//        }
//        //验签通过删uuid
//        replayUtil.removeRandom(random);
//        return true;
//    }
//}
