package com.cmcc.bean;

import java.io.Serializable;
import java.util.Date;

public class Result  implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status ;
	  private  String message;
	  private String nickname;
	  private String headimgurl ;
	  private String openid ;
	  private String telnum ;
	  private Date subscribeTime ;
	  
	  private String provinceCode;
	  private String cityCode;
	  
	  
	  private int phoneStatus;
	  
	  
	  private  int bindPhoneTime;
	  private  int subscribe_timeloc;
	  private  int unBindPhoneTime;
	  private  int unsubscribe_time;
	  
	  
	  private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBindPhoneTime() {
		return bindPhoneTime;
	}

	public void setBindPhoneTime(int bindPhoneTime) {
		this.bindPhoneTime = bindPhoneTime;
	}

	public int getSubscribe_timeloc() {
		return subscribe_timeloc;
	}

	public void setSubscribe_timeloc(int subscribe_timeloc) {
		this.subscribe_timeloc = subscribe_timeloc;
	}

	public int getUnBindPhoneTime() {
		return unBindPhoneTime;
	}

	public void setUnBindPhoneTime(int unBindPhoneTime) {
		this.unBindPhoneTime = unBindPhoneTime;
	}

	public int getUnsubscribe_time() {
		return unsubscribe_time;
	}

	public void setUnsubscribe_time(int unsubscribe_time) {
		this.unsubscribe_time = unsubscribe_time;
	}

	public int getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(int phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public Result() {
		super();
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public Date getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", nickname=" + nickname + ", headimgurl="
				+ headimgurl + ", openid=" + openid + ", telnum=" + telnum + ", subscribeTime=" + subscribeTime
				+ ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", phoneStatus=" + phoneStatus + "]";
	}
  
	  
	
	

}
