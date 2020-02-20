package com.lc.meq.pojo.vo;

import com.lc.meq.entity.SysRoles;
import com.lc.meq.entity.SysUsers;

public class SysRolesVo extends SysRoles {

//	private SysUsers sysUsers;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
