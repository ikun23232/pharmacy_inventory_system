package com.kgc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kgc.constant.ResponseCodeConstant;
import com.kgc.constant.ResponseMessageConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回结果构建处理
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultJsonUtil<T> implements Serializable {

    private int    code;
    private String msg;
    private T      data;

    private static final int DEFAULT_STATUS_CODE = 0;

    /**
     * construction
     * @param code 请求状态码
     * @param statusCode 信息状态码
     * @param msg  信息
     * @param data 数据
     */
    public ResultJsonUtil(int code, int statusCode, String msg, T data) {
        this.code       = code;
        this.msg        = msg;
        this.data       = data;
    }
    public ResultJsonUtil(){

    }

    public ResultJsonUtil success(T data){
        this.code       = ResponseCodeConstant.REQUEST_SUCCESS;
        this.msg        = ResponseMessageConstant.REQUEST_SUCCESS;
        this.data       = data;
        return this;
    }

    /**
     * 自定义返回参数
     * @return
     */
    public ResultJsonUtil customized(int code,String msg,T data){
        this.code       = code;
        this.msg        = msg;
        this.data       = null;
        return this;
    }

    /**
     * 请求失败
     * @param errorMsg
     * @return
     */
    public ResultJsonUtil fail(String errorMsg){
        this.code       = ResponseCodeConstant.REQUEST_FAILED;
        this.msg        = errorMsg;
        this.data       = null;
        return this;
    }



    public static String build(int code, int statusCode, String msg) {
        ResultJsonUtil<String> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, "");
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg);
    }

    public static String build(int code, int statusCode, String msg, JSONArray data) {
        ResultJsonUtil<JSONArray> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, data);
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg, JSONArray data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }


    public static String build(int code, int statusCode, String msg, Map data) {
        JSONObject jsonObjectData = JSONObject.parseObject(JSON.toJSONString(data));
        ResultJsonUtil<JSONObject> resultJsonUtil = new ResultJsonUtil<>(code, statusCode, msg, jsonObjectData);
        return resultJsonUtil.getResultJson();
    }

    public static String build(int code, String msg, Map data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }


    public static String build(int code, int statusCode, String msg, List data) {
        JSONArray jsonArrayData = JSONArray.parseArray(JSON.toJSONString(data));
        return ResultJsonUtil.build(code, statusCode, msg, jsonArrayData);
    }

    public static String build(int code, String msg, List data) {
        return ResultJsonUtil.build(code, ResultJsonUtil.DEFAULT_STATUS_CODE, msg, data);
    }


    private String getResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", this.code);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }


}