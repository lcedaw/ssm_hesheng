package com.lc.meq.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class SysUsers implements Serializable
{
	/**
	 * 考虑到兼容问题，为了让该类别Serializable向后兼容
	 */
	private static final long serialVersionUID = 4558649018717586673L;
	private String userUid;
	private String userCode;
	private String userName;
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String jsonString(SysUsers sysUsers) {
		return JSON.toJSONString(sysUsers);
	}
}
