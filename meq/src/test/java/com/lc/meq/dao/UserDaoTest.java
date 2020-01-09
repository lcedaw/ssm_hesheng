package com.lc.meq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.lc.meq.BaseTest;
import com.lc.meq.authorization.manager.TokenManager;
import com.lc.meq.authorization.model.TokenModel;
import com.lc.meq.common.annotation.IgnoreSecurity;
import com.lc.meq.common.utils.Base64Util;
import com.lc.meq.entity.SysUsers;

import redis.clients.jedis.Jedis;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private SysUsersDao sysUsersDao;

	@Autowired
	private TokenManager tokenManager;
	
//	@Test
	@IgnoreSecurity
	public void testQuertById() throws Exception{
		String uid = "a42b50ab-b436-4162-beda-ca73338eb3b2";
		SysUsers sysUsers = sysUsersDao.queryById(uid);
		
		TokenModel tokenModel;
		if(tokenManager.hasToken(sysUsers.getUserUid())) {
			tokenManager.deleteToken(sysUsers.getUserUid());
			tokenModel = tokenManager.createToken(sysUsers.getUserUid());    
		}else{
			//创建token
			tokenModel = tokenManager.createToken(sysUsers.getUserUid());
		}
		
//		SysUsers sysUsers = sysUsersDao.getSysUsers("55666", "1000");
		System.out.println(sysUsers.jsonString(sysUsers));
	}
	
	//@Test
	public void queryAll() throws Exception{
		List<SysUsers> sysUsers = sysUsersDao.queryUsersAll();
		JSONArray jsonArray = new JSONArray();
		for (SysUsers users : sysUsers) {
			jsonArray.add(users.jsonString(users));
		}
		System.out.println(jsonArray.toJSONString());
	}
	
	@Test
	public void decodeBase() {
		Jedis jedis = new Jedis("localhost");
		jedis.auth("123456");
		String value = jedis.get("google-site");
		System.out.println(value);
		Map<String, String> user = new HashMap<String, String>();
		user.put("name", "juner");
		user.put("age", "20");
		jedis.hmset("user", user);
		List<String> rsmap = jedis.hmget("user", "name");
		System.out.println(rsmap);
	}
	
}
