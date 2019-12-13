package com.lc.meq.authorization.manager.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.lc.meq.authorization.manager.TokenManager;
import com.lc.meq.authorization.model.TokenModel;

/**
 * 通过Redis存储和验证token的实现类
 * @author ljz
 * @date 2019-12-13
 */
public class RedisTokenManager implements TokenManager {

	private RedisTemplate<Long, String> redis;
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	public void setRedis(RedisTemplate<Long, String> redis) {
		this.redis = redis;
		//redis序列化方式
		redis.setKeySerializer(new JdkSerializationRedisSerializer());
	}
	
	@Override
	public TokenModel createToken(String userUid) {
		//生成的uuid带有"-",需要替换
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String timestamp = simpleDateFormat.format(new Date());
		String token = String.format("%s_%s_%s", userUid,timestamp,uuid);
		return null;
	}

	@Override
	public boolean checkToken(TokenModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TokenModel getToken(String authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteToken(String userUid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasToken(String userUid) {
		// TODO Auto-generated method stub
		return false;
	}
}
