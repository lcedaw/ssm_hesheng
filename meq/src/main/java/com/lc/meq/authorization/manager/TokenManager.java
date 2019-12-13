package com.lc.meq.authorization.manager;

import com.lc.meq.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 * @author ljz
 * @date 2019-12-13
 */
public interface TokenManager {
	
	/**
	 * 创建一个token关联上指定用户
	 * @param userUid 用户的Uid
	 * @return 生产的token
	 */
	TokenModel createToken(String userUid);
	
	/**
	 * 检查token是否有效
	 * @param model
	 * @return 
	 */
	boolean checkToken(TokenModel model);
	
	/**
	 * 从字符串中解析token
	 * @param authentication 加密后的字符串
	 * @return
	 */
	TokenModel getToken(String authentication);
	
	/**
	 * 清除token
	 * @param userUid 登录用户的uid
	 */
	void deleteToken(String userUid);
	
	/**
	 * 保证一个用户在一个时间段只有一个可用Token
	 * @param userUid
	 * @return
	 */
	boolean hasToken(String userUid);
}
