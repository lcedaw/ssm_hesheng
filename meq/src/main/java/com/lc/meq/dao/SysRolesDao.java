package com.lc.meq.dao;

import com.lc.meq.entity.SysRoles;

public interface SysRolesDao {

	/**
	 * 通过id查询角色
	 * @param roleUid
	 * @return
	 */
	SysRoles queryById(String roleUid);
	
	/**
	 * 通过id更新角色
	 * @param roleUid
	 */
	void updateSysRolesById(String roleUid);
}
