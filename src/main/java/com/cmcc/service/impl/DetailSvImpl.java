package com.cmcc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.bean.Detail;
import com.cmcc.mapper.DetailMapper;
import com.cmcc.utils.WechatUtils;

@Service
public class DetailSvImpl implements DetailMapper{
	@Autowired
   private DetailMapper detailMapper;
	
	public void insert(Detail detail) {
		detailMapper.insert(detail);
	}



	public Detail selectById(int id) {
		return detailMapper.selectById(id);
	}





	public void update(Detail detail) {
		 detailMapper.update(detail);
	}


	/**
	 * getAccessToken
	 * @param appId
	 * @param secret
	 * @param code
	 * @return Detail
	 */
	public  Detail getAccessTokenDetail(String appId,String secret,String code) {
		  String openId = null;
		  String accessToken = null;
		  String refresh_token = null;
		  int expires_in = 0;
		  String scope = null;
		  String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		  JSONObject jsonObject = WechatUtils.getJsonObjFromHttp(url);
		  accessToken = jsonObject.getString("access_token");
	      openId = jsonObject.getString("openid");
	      expires_in = jsonObject.getIntValue("expires_in");
	    		
	      scope = jsonObject.getString("scope");
	      refresh_token = jsonObject.getString("refresh_token");
	      openId = jsonObject.getString("openid");
	      Detail detail = new Detail(openId, accessToken, refresh_token, expires_in, scope);
	      return detail;
		}

}
