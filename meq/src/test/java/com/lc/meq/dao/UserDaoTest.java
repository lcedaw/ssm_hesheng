package com.lc.meq.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.lc.meq.BaseTest;
import com.lc.meq.authorization.manager.TokenManager;
import com.lc.meq.authorization.model.TokenModel;
import com.lc.meq.common.annotation.IgnoreSecurity;
import com.lc.meq.entity.SysUsers;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private SysUsersDao sysUsersDao;

	@Autowired
	private TokenManager tokenManager;
	
	@Test
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
	
}
