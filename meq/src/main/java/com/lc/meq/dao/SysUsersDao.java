package com.lc.meq.dao;

import java.util.List;

import com.lc.meq.entity.SysUsers;

public interface SysUsersDao {

	/**
	 * 通过ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	SysUsers queryById(String id);
	
	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<SysUsers> queryUsersAll();
}
