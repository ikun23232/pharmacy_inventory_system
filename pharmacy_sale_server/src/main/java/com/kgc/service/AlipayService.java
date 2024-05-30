package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import java.util.Map;

public interface AlipayService {

	public Message createOrder(XsOrder xsOrder);

	public Message alipayNotify(Map<String, String> values);
}
