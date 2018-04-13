package com.tank.es.core.common;

/**
 * 
* @ClassName: Result  
* @Description: 统一API响应结果封装
* @author duanzhiwei
* @date 2018年1月16日 上午11:50:25  
*  
* @param <O>
 */
public class Result <O> {
    /**
     * code:响应码
     */
    private int code;
    /**
     * message:响应消息
     */
    private String message;
    /**
     * data:响应数据
     */
    private O data;
    
    public Result() {
        
    }
    
    public Result(O data) {
        this.data = data;
    }

    public Result(ResultCode success) {
        this.code = success.getCode();
        this.message = success.getMsg();
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Result(ResultCode success, O data) {
        this.code = success.getCode();
        this.message = success.getMsg();
        this.data = data;
    }

    public Result<O> setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result<O> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<O> setCodeMsg(ResultCode resultCode) {
    	this.code = resultCode.getCode();
    	this.message = resultCode.getMsg();
    	return this;
    }
    
    public O getData() {
        return data;
    }

    public Result<O> setData(O data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
    }
    
    public static <O> Result<O> ok() {
        return new Result<O>(ResultCode.SUCCESS, null);
    }

    public static <O> Result<O> ok(O data) {
        return new Result<O>(ResultCode.SUCCESS, data);
    }

    public static <O> Result<O> fail(String message) {
        return new Result<O>(ResultCode.FAIL.getCode(), message);
    }
    
    public static <O> Result<O> build(ResultCode resultCode) {
        return new Result<O>(resultCode, null);
    }

    public static <O> Result<O> build(ResultCode resultCode, O data) {
        return new Result<O>(resultCode, data);
    }
    
}
