package com.lc.meq.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lc.meq.BaseTest;
import com.lc.meq.entity.SysUsers;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private SysUsersDao sysUsersDao;

	@Test
	public void testQuertById() throws Exception{
		String uid = "8f02bda8-e22d-4ea2-b1cb-0873d1be8b6e";
		SysUsers sysUsers = sysUsersDao.queryById(uid);
		System.out.println(sysUsers.jsonString(sysUsers));
	}
	
}
