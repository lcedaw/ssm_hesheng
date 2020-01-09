package com.lc.meq.service;

import org.springframework.stereotype.Service;

import com.lc.meq.entity.SysRoles;

@Service
public interface SysRolesService {
	
	/**
	 * 通过ID查询角色
	 * @param roleUid
	 * @return
	 */
	SysRoles queryById(String roleUid);
	
	/**
	 * 用过ID更新角色
	 * @param sysRoles
	 */
	void updateById(SysRoles sysRoles);
}
