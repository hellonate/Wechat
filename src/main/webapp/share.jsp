<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<%pageContext.setAttribute("basePath", request.getContextPath()+"/");%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${basePath}js/jquery.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<meta charset="UTF-8">
<title>分享到朋友圈</title>
<script>
	    $(function(){
	    	var  timestamp1 ="";
			var  nonceStr1 ="";
			var  appId1="";
			var  signature="";
			
			$.ajax({
				url:"secwechat/share2WX",
				//url:"test1/back",
				type:'get',
				async:false,
				cache:false,
				dataType:'JSON',
				success:function(data){	
				 timestamp1 = 	data.timestamp;
				 nonceStr1 = 	data.nonceStr;
				 appId1 = 	data.appId;
				 signature = 	data.signature;
				 alert(timestamp1+","+nonceStr1+","+appId1+","+signature);
		    }});
			
			
			
		    wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: appId1, // 必填，公众号的唯一标识
		    timestamp:timestamp1, // 必填，生成签名的时间戳
		    nonceStr: nonceStr1, // 必填，生成签名的随机串
		    signature: signature,// 必填，签名
		    
		    jsApiList: ['onMenuShareAppMessage','onMenuShareQQ','onMenuShareTimeline','onMenuShareWeibo','onMenuShareQZone'] // 必填，需要使用的JS接口列表
		});
	   
		    //config配置信息失败后执行
		    wx.error(function(res){
				//config信息验证失败会执行error函数	
				alert("config信息验证失败");
			});
		//通过ready接口处理成功验证
		wx.ready(function(){
			//config 信息验证成功后，执行ready方法
		
				wx.onMenuShareTimeline({
					title:'分享到朋友圈',
				    link:window.location.href,
				  //  imgUrl:'',
				    success:function(){
				    	//分享成功后执行的回调函数
				    	alert("success");
				    },
				    cancel:function(){
				    	alert("failed");
				    }
				});
				
				
			});
			
			
	
		
		//判断当前客户端是否支持指定的JS接口
		wx.checkJsApi({
			jsApiList:['onMenuShareTimeline'],
			success:function(res){
				//以键值对的形式返回
			//	{"checkResult":{"onMenuShareTimeline":true},"errMsg":"checkJsApi:ok"}
				
			}
		});	
});
</script>
</head>
<body>
         <h3>${share.timestamp}</h3><br/>
         <h3>${share.nonceStr}</h3><br/>
         <h3>${share.appId}</h3><br/>
         <h3>${share.signature}</h3><br/>
			
			
		
	
	
           
</body>
</html>