package com.kgc.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.Alipay;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private Alipay alipay;

	@Autowired
	private SaleOrderMapper saleOrderMapper;


	@Override
	public Message createOrder(XsOrder xsOrder) {
		AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGateway(),alipay.getAppId(),alipay.getPrivateKey(),"json","utf-8",alipay.getAlipayPublicKey(),"RSA2");
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//异步接收地址，仅支持http/https，公网可访问
		request.setNotifyUrl(alipay.getNotifyUrl());
		//同步跳转地址，仅支持http/https
		request.setReturnUrl(alipay.getReturnUrl());

		/******必传参数******/
		JSONObject bizContent = new JSONObject();
		//商户订单号，商家自定义，保持唯一性
		bizContent.put("out_trade_no", 1234);
		//支付金额，最小值0.01元
		bizContent.put("total_amount", 10);
		//订单标题，不可使用特殊符号
		bizContent.put("subject", "销售订单");
		//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
		bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//		bizContent.put("timeout_express","1m");

		request.setBizContent(bizContent.toString());
		AlipayTradePagePayResponse response=null;

		try {
			response = alipayClient.pageExecute(request,"GET");
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 如果需要返回GET请求，请使用
		// AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
		String pageRedirectionData = response.getBody();
		Message message=new Message();
		message.setData(pageRedirectionData);
		if(response.isSuccess()){
			message.setCode("200");
			message.setMessage("create success!");
		} else {
			message.setCode("500");
			message.setMessage("create fail!");
		}
		return message;
	}

	@Override
	public Message alipayNotify(Map<String, String> values) {
		Message responseMessage=new Message();
		if(values==null||values.isEmpty()) {
			responseMessage.setCode("500");
			responseMessage.setMessage("values is empty!");
			return responseMessage;
		}
		String trade_no=values.get("out_trade_no");
		String total_amount=values.get("total_amount");
		String subject=values.get("subject");
		String trade_status=values.get("trade_status");
		//校验支付宝签名
		boolean signVerified =false;
		try {
			signVerified = AlipaySignature.rsaCheckV2(values,alipay.getAlipayPublicKey(),"utf-8","RSA2");
			if(signVerified) {
				if(trade_status.equals("TRADE_SUCCESS")) {
					//更新订单状态
//					int count=orderMapper.updateOrderStatus(trade_no);
//					tradeRecords.setTrade_status("TRADE_SUCCESS");
//					alipayMapper.updateTradeStatus(tradeRecords);
//					if(count>0){
//						responseMessage.setCode("200");
//						responseMessage.setMsg("success");
//					}
				}
			} else {
				responseMessage.setCode("500");
				responseMessage.setMessage("fail");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	private static String extractPayOrderIdFromUrl(String url) {
		// 假设URL格式为: "https://excashier-sandbox.alipaydev.com/standard/auth.htm?payOrderId=xxx"
		String[] parts = url.split("\\?");
		if (parts.length > 1) {
			String query = parts[1];
			parts = query.split("&");
			for (String param : parts) {
				if (param.startsWith("payOrderId=")) {
					return param.split("=")[1];
				}
			}
		}
		return null;
	}

}
