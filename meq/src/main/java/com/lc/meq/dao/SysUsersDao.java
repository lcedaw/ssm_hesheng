package com.lc.meq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 通过账号和名称查询用户
	 * 
	 * @param userCode
	 * @param userName
	 * @return
	 */
	SysUsers getSysUsers(@Param("userCode") String userCode ,@Param("userName") String userName);
}
