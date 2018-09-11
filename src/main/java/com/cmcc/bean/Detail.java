package com.cmcc.bean;

public class Detail {
	private Integer id;
	private String openId;
	private String accessToken;
	private String refresh_token;
	private int expire_in;
	private String scope;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public int getExpire_in() {
		return expire_in;
	}
	public void setExpire_in(int expire_in) {
		this.expire_in = expire_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Detail(String openId, String accessToken, String refresh_token, int expire_in, String scope) {
		super();
		this.openId = openId;
		this.accessToken = accessToken;
		this.refresh_token = refresh_token;
		this.expire_in = expire_in;
		this.scope = scope;
	}
	public Detail() {
		super();
	}
	@Override
	public String toString() {
		return "Detail [id=" + id + ", openId=" + openId + ", accessToken=" + accessToken + ", refresh_token="
				+ refresh_token + ", expire_in=" + expire_in + ", scope=" + scope + "]";
	}

	
	
    
}
