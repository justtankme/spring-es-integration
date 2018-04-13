package com.tank.es.core.common;

/**
 * 
* @ClassName: ResultCode  
* @Description: 响应码枚举，参考HTTP状态码的语义
* @author duanzhiwei
* @date 2018年1月16日 上午11:40:47  
*
 */
public enum ResultCode {
    /**
     * SUCCESS:TODO
     */
    SUCCESS(200, "成功"),
    /**
     * FAIL:TODO
     */
    FAIL(400, "失败"),
    /**
     * UNAUTHORIZED:TODO
     */
    UNAUTHORIZED(401, "未认证"),
    /**
     * NOT_FOUND:TODO
     */
    NOT_FOUND(404, "接口不存在"),
    /**
     * INTERNAL_SERVER_ERROR:TODO
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
	/**
	 * TOKEN_EMPTY:TODO
	 */
	TOKEN_EMPTY(1, "请求未带token"),
	/**
	 * USER_LOGIN_FAILED_NAMEPWDERR:用户名或密码错误
	 */
	USER_LOGIN_FAILED_NAMEPWDERR(101010, "用户名或密码错误");

    /**
     * code:TODO
     */
    private final int code;
    /**
     * msg:TODO
     */
    private final String msg;
    
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public int getCode() {
        return code;
    }
	
	public String getMsg() {
		return msg;
	}
}
