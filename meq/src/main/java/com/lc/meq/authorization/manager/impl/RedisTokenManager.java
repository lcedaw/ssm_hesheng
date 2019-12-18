package com.lc.meq.authorization.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.lc.meq.authorization.manager.TokenManager;
import com.lc.meq.authorization.model.TokenModel;
import com.lc.meq.common.constant.Constants;
import com.lc.meq.common.utils.Base64Util;
import com.lc.meq.common.utils.StringUtils;

/**
 * 通过Redis存储和验证token的实现类
 * 
 * @author ljz
 * @date 2019-12-13
 */
@Component
public class RedisTokenManager implements TokenManager {

	private RedisTemplate<String, String> redis;
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	public void setRedis(RedisTemplate<String, String> redis) {
		this.redis = redis;
		// redis序列化方式
		redis.setKeySerializer(new JdkSerializationRedisSerializer());
	}

	@Override
	public TokenModel createToken(String userUid) {
		// 生成的uuid带有"-",需要替换
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String timestamp = simpleDateFormat.format(new Date());
		String token = String.format("%s_%s_%s", userUid, timestamp, uuid);
		TokenModel tokenModel = new TokenModel(userUid, uuid, timestamp);
		// 存储到redis并设置过期时间
		redis.boundValueOps(userUid).set(Base64Util.encodeData(token), Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return tokenModel;
	}

	@Override
	public boolean checkToken(TokenModel model) {
		if(model == null) return false;
		String token = redis.boundValueOps(model.getUserUid()).get();
		if(token == null || !(Base64Util.decodeData(token)).equals(model.getToken())) {
			return false;
		}
		//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间(2个小时)
		redis.boundValueOps(model.getUserUid()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return true;
	}

	@Override
	public TokenModel getToken(String authentication) {
		if(StringUtils.isEmpty(authentication)) return null;
		String[] param = authentication.split(",");
		if(param.length != 3) return null;
		//使用userId和源token简单拼接成的token，可以增加加密措施
		String userUid = param[0];
		String timestamp = param[1];
		String uuid = param[2];
		return new TokenModel(userUid, uuid, timestamp);
	}

	@Override
	public void deleteToken(String userUid) {
		if(redis.hasKey(userUid)) redis.delete(userUid);
	}

	@Override
	public boolean hasToken(String userUid) {
		return StringUtils.isNotEmpty(userUid);
	}
}
