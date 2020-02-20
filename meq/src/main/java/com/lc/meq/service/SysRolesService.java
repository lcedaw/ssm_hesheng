package com.lc.meq.service;

import org.springframework.stereotype.Service;

import com.lc.meq.entity.SysRoles;
import com.lc.meq.pojo.vo.SysRolesVo;

@Service
public interface SysRolesService {
	
	/**
	 * 通过ID查询角色
	 * @param roleUid
	 * @return
	 */
	SysRolesVo queryById(String roleUid);
	
	/**
	 * 用过ID更新角色
	 * @param sysRoles
	 */
	void updateById(SysRoles sysRoles);
}
