package com.kgc.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.*;
import com.kgc.feign.*;
import com.kgc.service.AlipayService;
import com.kgc.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private Alipay alipay;

	@Autowired
	private SaleOrderMapper saleOrderMapper;

	@Autowired
	private StockDetailFeign stockDetailFeign;
	@Autowired
	private SaleOutWarehouseFeign saleOutWarehouseFeign;
	@Autowired
	private KcOutintodetialFeign kcOutintodetialFeign;
	@Autowired
	private CwXsysFeign cwXsysFeign;
	@Autowired
	private CwAccountsFeign cwAccountsFeign;
	@Autowired
	private CwInvoiceFeign cwInvoiceFeign;


	@Override
	public Message createOrder(String orderNo,int totalPrice) {
		AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGateway(),alipay.getAppId(),alipay.getPrivateKey(),"json","utf-8",alipay.getAlipayPublicKey(),"RSA2");
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//异步接收地址，仅支持http/https，公网可访问
		request.setNotifyUrl(alipay.getNotifyUrl());
		//同步跳转地址，仅支持http/https
		request.setReturnUrl(alipay.getReturnUrl());

		/******必传参数******/
		JSONObject bizContent = new JSONObject();
		//商户订单号，商家自定义，保持唯一性
		bizContent.put("out_trade_no",orderNo);
		//支付金额，最小值0.01元
		bizContent.put("total_amount", totalPrice);
		//订单标题，不可使用特殊符号
		bizContent.put("subject","销售订单");
		//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
		bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//		bizContent.put("timeout_express","1m");

		request.setBizContent(bizContent.toString());
		AlipayTradePagePayResponse response=null;
		try {
			response = alipayClient.pageExecute(request,"POST");
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 如果需要返回GET请求，请使用
		// AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
		String pageRedirectionData = response.getBody();
		Message responseMessage=new Message();
		responseMessage.setData(pageRedirectionData);
		if(response.isSuccess()){
			responseMessage.setCode("200");
			responseMessage.setMessage("create success!");
		} else {
			responseMessage.setCode("500");
			responseMessage.setMessage("create fail!");
		}
		return responseMessage;
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
//					SysUser loginUser = (SysUser) StpUtil.getSession().get("user");
					//更新订单状态
					XsOrder xsOrder=saleOrderMapper.getSaleOrderByOrderNo(trade_no);
					List<BaseMedicine> baseMedicineDetailList=saleOrderMapper.getSaleOrderDetailByOrderNo(trade_no);
					//生成销售出库单
					String code= CodeUtil.createCode("XXCK");
					KcSalefromware kcSalefromware=new KcSalefromware();
					kcSalefromware.setCode(code);
					kcSalefromware.setOrderNo(xsOrder.getOrderNo());
					Message saleOutWarehouseMessage=saleOutWarehouseFeign.addSaleOutWarehouse(kcSalefromware);
					boolean result=true;
					for(BaseMedicine orderMedicine:baseMedicineDetailList){
						orderMedicine.setCode(xsOrder.getOrderNo());
						//更新库存明细
						Message stockDetailMessage=stockDetailFeign.reduceStockDetailByMedicineId(orderMedicine.getMedicineId(),orderMedicine.getQuantity(),orderMedicine.getBatchCode());
						//生成销售出库明细单
						String code2=CodeUtil.createCode("CRKMX");
						KcOutintodetial kcOutintodetial=new KcOutintodetial();
						kcOutintodetial.setCode(code2);
						kcOutintodetial.setTypeId(5);
//						kcOutintodetial.setCreateBy(loginUser.getUserid());
						kcOutintodetial.setCreateBy(1);
						kcOutintodetial.setCreateDate(xsOrder.getOrderDate());
						kcOutintodetial.setOrderCode(xsOrder.getOrderNo());
						kcOutintodetial.setMedicineId(orderMedicine.getMedicineId());
						kcOutintodetial.setToStockMoney(orderMedicine.getTotalPrice());
						kcOutintodetial.setBatchCode(orderMedicine.getBatchCode());
						kcOutintodetial.setToStockQuantity(orderMedicine.getQuantity());
						kcOutintodetial.setPrice(orderMedicine.getSalePrice());
						Message kcOutintodetialMessage=kcOutintodetialFeign.addKcOutintodetial(kcOutintodetial);
//						if("200".equals(stockDetailMessage.getCode())&&"200".equals(kcOutintodetialMessage.getCode())){
//							result=false;
//						}
					}
					//生成销售收款单
					CwXsys cwXsys=new CwXsys();
					String code3=CodeUtil.createCode("CWXSYS");
					cwXsys.setCode(code3);
					cwXsys.setOriginalOrder(xsOrder.getOrderNo());
					cwXsys.setCreateTime(xsOrder.getOrderDate());
					cwXsys.setCost(xsOrder.getTotalPrice());
//					cwXsys.setCreateBy(loginUser.getUserid());
					cwXsys.setCreateBy(1);
					Message cwXsysMessage=cwXsysFeign.addCwXsys(cwXsys);
					//生成销售应收流水
					CwAccounts cwAccounts=new CwAccounts();
					String code4=CodeUtil.createCode("XSYSLS");
					cwAccounts.setCode(code4);
					cwAccounts.setOrderCode(xsOrder.getOrderNo());
					cwAccounts.setCategoryId(3);
					cwAccounts.setCost(xsOrder.getTotalPrice());
					cwAccounts.setBankAcountId(xsOrder.getBankAccountId());
					cwAccounts.setDescription(xsOrder.getRemark());
					cwAccounts.setCreateTime(xsOrder.getOrderDate());
//					cwAccounts.setCreateBy(loginUser.getUserid());
					cwAccounts.setCreateBy(1);
					Message cwAccountsMessage=cwAccountsFeign.insertCwAccounts(cwAccounts);
					//生成销售应收发票
					CwInvoice cwInvoice=new CwInvoice();
					String code5=CodeUtil.createCode("XSYSFP");
					cwInvoice.setCode(code5);
					cwInvoice.setCategoryId(3);
					cwInvoice.setOrderNumber(xsOrder.getOrderNo());
					cwInvoice.setCreateTime(xsOrder.getOrderDate());
//					cwInvoice.setCreateBy(loginUser.getUserid());
					cwInvoice.setCreateBy(1);
					cwInvoice.setCost(xsOrder.getTotalPrice());
					Message cwInvoiceMessage=cwInvoiceFeign.addCwInvoice(cwInvoice);
					if("200".equals(saleOutWarehouseMessage.getCode())&&"200".equals(cwXsysMessage.getCode())&&"200".equals(cwAccountsMessage.getCode())&&"200".equals(cwInvoiceMessage.getCode())){
						return Message.success();
					}else{
						return Message.error();
					}
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

}
