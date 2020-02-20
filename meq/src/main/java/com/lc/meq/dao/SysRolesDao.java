package com.lc.meq.dao;

import com.lc.meq.entity.SysRoles;
import com.lc.meq.pojo.vo.SysRolesVo;

public interface SysRolesDao {

	/**
	 * 通过id查询角色
	 * @param roleUid
	 * @return
	 */
	SysRolesVo queryById(String roleUid);
	
	/**
	 * 通过id更新角色
	 * @param roleUid
	 */
	void updateById(SysRoles sysRoles);
}
