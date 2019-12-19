package com.lc.meq.common.exception;

/**
 * Token过期时抛出异常
 * @author ljz
 * @date 2019-12-19
 */
public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884526892380308872L;

	private String message;
	
	public TokenException(String message) {
    	//调用父类无参的构造方法
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
