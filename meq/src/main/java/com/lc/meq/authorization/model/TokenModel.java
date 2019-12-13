package com.lc.meq.authorization.model;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * 
 * @author ljz
 * @date 2019-12-13
 */
public class TokenModel {

	/**
	 * 用户Uid
	 */
	private String userUid;

	/**
	 * 随机生成的uuid
	 */
	private String uuid;

	/**
	 * 时间戳
	 */
	private String timestamp;

	public TokenModel(String userUid, String uuid, String timestamp) {
		this.userUid = userUid;
		this.uuid = uuid;
		this.timestamp = timestamp;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return String.format("%s_%s_%s", userUid, timestamp, uuid);
	}
}
