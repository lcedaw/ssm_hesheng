package com.lc.meq.service;

import org.springframework.stereotype.Service;

import com.lc.meq.entity.SysUsers;

@Service
public interface SysUsersService {
	/**
	 * 通过账号和名称查询用户
	 * 
	 * @param userCode
	 * @param userName
	 * @return
	 */
	SysUsers getSysUsers(String userCode ,String userName);
}
