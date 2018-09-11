package com.cmcc.bean;


public class Share {
	private String timestamp;
	private String nonceStr;
	private String appId;
	private String signature;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Share() {
		super();
	}
	@Override
	public String toString() {
		return "Share [timestamp=" + timestamp + ", nonceStr=" + nonceStr + ", appId=" + appId + ", signature="
				+ signature + "]";
	}

	
}
