package com.lc.meq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.meq.dao.SysUsersDao;
import com.lc.meq.entity.SysUsers;
import com.lc.meq.service.SysUsersService;

/**
 * SysUsersService接口实现类
 * @author ljz
 * @date 2019-12-13
 */
@Service
public class SysUsersServiceImpl implements SysUsersService {

	@Autowired
	SysUsersDao sysUsersDao;
	
	//@Override
	public SysUsers getSysUsers(String userCode, String userName) {
		return sysUsersDao.getSysUsers(userCode, userName);
	}
	
	public List<SysUsers> queryUsersAll(){
		return sysUsersDao.queryUsersAll();
	}
}
