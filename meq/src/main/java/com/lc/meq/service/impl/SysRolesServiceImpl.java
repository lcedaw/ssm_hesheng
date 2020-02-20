package com.lc.meq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.meq.dao.SysRolesDao;
import com.lc.meq.entity.SysRoles;
import com.lc.meq.pojo.vo.SysRolesVo;
import com.lc.meq.service.SysRolesService;

/**
 * SysRolesService接口类实现
 * @author ljz
 * @date 2019-01-08
 */

@Service
public class SysRolesServiceImpl implements SysRolesService{

	@Autowired
	SysRolesDao sysRolesDao;
	
	public SysRolesVo queryById(String roleUid) {
		return sysRolesDao.queryById(roleUid);
	}

	@Override
	public void updateById(SysRoles sysRoles) {
		sysRolesDao.updateById(sysRoles);
	}
}
