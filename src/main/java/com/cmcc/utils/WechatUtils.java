package com.cmcc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;



import com.alibaba.fastjson.JSONObject;
import com.cmcc.bean.Detail;
import com.cmcc.bean.User;
import com.cmcc.config.Config;
import com.cmcc.utils.WechatUtils;
import com.sun.net.ssl.HttpsURLConnection;

public class WechatUtils {
	
	private static String openId = null;
	private static String nickName = null;
	private static String sex = null;
	private static String province = null;
	private static String country = null;
	private static String city = null;
	//************************************************非************************************************
	/**
	 * get code
	 * @param appID
	 * @param redirectUrl
	 * @return
	 */
/*	public static String getCode(String appID,String redirectUrl) {
		String code = null;
		//转到授权页面
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appID+"&redirect_uri="+URLEncoder.encode(redirectUrl)+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		JSONObject jsonObject = getJsonObjFromHttp(url);
		code = jsonObject.getString("code");
		return code;
	}*/
	
	/**
	 * getAccessToken
	 * @param appId
	 * @param secret
	 * @param code
	 * @return Detail
	 */
	public static Detail getAccessTokenDetail(String appId,String secret,String code) {
		  String openId = null;
		  String accessToken = null;
		  String refresh_token = null;
		  int expires_in = 0;
		  String scope = null;
		  String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		  JSONObject jsonObject = getJsonObjFromHttp(url);
		  accessToken = jsonObject.getString("access_token");
	      openId = jsonObject.getString("openid");
	      expires_in = jsonObject.getIntValue("expires_in");
	    		
	      scope = jsonObject.getString("scope");
	      refresh_token = jsonObject.getString("refresh_token");
	      openId = jsonObject.getString("openid");
	      Detail detail = new Detail(openId, accessToken, refresh_token, expires_in, scope);
	      return detail;
		}
	
	/**
	 * saveToProperty
	 * @param accessToken
	 * @throws IOException
	 */
	public static void saveToProperty(String accessToken)  {
		Properties properties = new Properties();
		InputStream inputStream = WechatUtils.class.getClassLoader().getResourceAsStream(Config.FILENAME);
		try {
			properties.load(inputStream);
			inputStream.close();
			
		   // URL url = WechatUtils.class.getClassLoader().getResource(Config.FILENAME);
 		    FileOutputStream fos = new FileOutputStream(new File(Config.FILEPATH));
 		    properties.setProperty("access_token", accessToken);
		    //save time
		    properties.setProperty("last_time", System.currentTimeMillis()+"");
		    properties.store(fos, null);
		    fos.close();
		    
		    //TODO
		    System.out.println("accessToken after saved =>"+properties.getProperty("access_token")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取参数
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getProperty(String key) throws IOException {
		
		Properties properties = new Properties();
		InputStream inputStream = WechatUtils.class.getClassLoader().getResourceAsStream(Config.FILENAME);
		properties.load(inputStream);
		inputStream.close();
	   // URL url = WechatUtils.class.getClassLoader().getResource(Config.FILENAME);
	    String value = (String) properties.get(key);
	    //TODO
	
		return value;
	}
	
	
	
	
	/**
	 * validate
	 * @param appID
	 * @param appSecret
	 * @return true/false
	 * @throws IOException 
	 */
	
	public static boolean validateAccessToken() throws IOException {
		String value = getProperty("last_time");
		Long last_time = Long.parseLong(value);
		if ((System.currentTimeMillis() - last_time)/1000 > Config.EXPIRE_IN) {
			System.out.println("System.currentTimeMillis()=>"+System.currentTimeMillis());
			System.out.println("Config.LAST_TIME=>"+last_time);
			return false;
		}
		return true;
	}
   
	/**
	 *  reObtainAccessToken
	 */
	public static Detail reObtainAccessToken(String refresh_token) {
		
		// get refresh_token
		String re_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+Config.APPTest_ID+"&grant_type=refresh_token&refresh_token="+refresh_token+"";
		JSONObject jsonObject = getJsonObjFromHttp(re_url);
		
		String access_token = jsonObject.getString("access_token");
		int	    expires_in= Integer.parseInt(jsonObject.getString("expires_in"));
		String	refreshToken= jsonObject.getString("refresh_token");
		String	openid= jsonObject.getString("openid");
		String	scope= jsonObject.getString("scope");
	    //保存到peoperty
		Detail detail = new Detail(openid, access_token, refresh_token, expires_in, scope);
		
		return detail;
	}

   /**
    * getUserInfo
    * @param detail
    * @return User
    */
	
	public static User getUserInfo(Detail detail) {
	
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+detail.getAccessToken()+"&openid="+detail.getOpenId()+"&lang=zh_CN";
		JSONObject jsonObject = getJsonObjFromHttp(url);
		openId = (String) jsonObject.get("openid");
		nickName = jsonObject.getString("nickname");
		sex = jsonObject.getString("sex");
		province = jsonObject.getString("provice");
		country = jsonObject.getString("country");
		User user = new User(openId, nickName, sex, province, city, country);
		return user;
	}
	
	
	/**
	  * getJsonObjFromHttp
	 * @param reqUrl
	 * @return JSONObject
	 */
	public synchronized static JSONObject getJsonObjFromHttp(String reqUrl) {
		JSONObject jsonObj = null;
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			 // URL url = new URL(reqUrl);
			//URL url = new URL(null,reqUrl,new sun.net.www.protocol.http.Handler());
			URL url= new URL(null, reqUrl);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			System.out.println("huc=>"+huc);
			huc.setDoOutput(true);
			huc.setDoInput(true);
			huc.setUseCaches(false);
			huc.setRequestMethod("GET");
			huc.connect();
			
			InputStream inputStream = huc.getInputStream();
			//TODO
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			 while ((str = bufferedReader.readLine()) != null) {
				 stringBuffer.append(str);
	            }
			    bufferedReader.close();
	            inputStreamReader.close();
	            // 
	            inputStream.close();
	            inputStream = null;
	            huc.disconnect();
	            jsonObj = JSONObject.parseObject(stringBuffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	
	/**
	  * getJsonObjFromHttp
	 * @param reqUrl
	 * @return JSONObject
	 */
	public synchronized static JSONObject getJsonObjFromHttps(String reqUrl) {
		JSONObject jsonObj = null;
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			 // URL url = new URL(reqUrl);
			URL url = new URL(null,reqUrl,new sun.net.www.protocol.http.Handler());
			//URL url= new URL(null, reqUrl);
			HttpsURLConnection huc = (HttpsURLConnection) url.openConnection();
			System.out.println("huc=>"+huc);
			huc.setDoOutput(true);
			huc.setDoInput(true);
			huc.setUseCaches(false);
			huc.setRequestMethod("GET");
			huc.connect();
			
			InputStream inputStream = huc.getInputStream();
			//TODO
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			 while ((str = bufferedReader.readLine()) != null) {
				 stringBuffer.append(str);
	            }
			    bufferedReader.close();
	            inputStreamReader.close();
	            // 
	            inputStream.close();
	            inputStream = null;
	            huc.disconnect();
	            jsonObj = JSONObject.parseObject(stringBuffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
  /**
   * 得到accessToken
   * @param appID
   * @param appSecret
   * @return
   */
	public static String getAccessToken(String appID,String appSecret) {
		 String access_token=null ;
		 String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                 + appID + "&secret=" + appSecret;		
		 JSONObject jsonObject = getJsonObjFromHttp(url);
		 access_token = (String) jsonObject.get("access_token");
		 return access_token;
	}
	
	
	

}
