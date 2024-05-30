package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.AlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlSchema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@RestController
public class AlipayController {

	private Logger logger= LoggerFactory.getLogger(getClass());

	@Autowired
	private AlipayService alipayService;

	@RequestMapping("createOrder")
	public Message createOrder(HttpServletResponse response,XsOrder xsOrder) {
		logger.info("AlipayController createOrder is start...");
		response.setContentType("text/html;charset=utf-8");
		Message message = alipayService.createOrder(null);
		logger.debug("AlipayController createOrder message:" + message);

		PrintWriter pWriter=null;
		try {
			pWriter=response.getWriter();
			if("200".equals(message.getCode())) {
				pWriter.print(message.getData());
			}
		} catch (IOException e) {
			logger.error("AlipayController createOrder is error, message:"+message);
			e.printStackTrace();
		}
		if(pWriter!=null) {
			pWriter.print("error");
		}
		return message;

	}
	
	@RequestMapping("alipayNotify")
	public void alipayNotify(HttpServletResponse response,HttpServletRequest request) {
		logger.info("AlipayController alipayNotify is start...");
		Map<String, String[]> values=request.getParameterMap();
		logger.debug("AlipayController alipayNotify values:"+values);
		Map<String, String> map=new HashMap<>();
		for(Entry entry:values.entrySet()) {
			String[] value=(String[]) entry.getValue();
			String key=(String) entry.getKey();
			StringBuffer sBuffer=new StringBuffer();
			for(int i=0;i<value.length;i++) {
				if(i==value.length-1) {
					sBuffer.append(value[i]);
				}else {
					sBuffer.append(value[i]+",");
				}
			}
			map.put(key, sBuffer.toString());
		}
		map.remove("sign_type");
		logger.debug("AlipayController alipayNotify map:"+map);
		Message message=alipayService.alipayNotify(map);
		logger.debug("AlipayController alipayNotify message:"+message);
		try {
			PrintWriter pWriter=response.getWriter();
			pWriter.print(message.getMessage());
		} catch (IOException e) {
			logger.error("AlipayController alipayNotify is error,message:"+message);
			e.printStackTrace();
		}
		
	}
}
