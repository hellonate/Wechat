<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>分享到朋友圈</title>
<%pageContext.setAttribute("basePath", request.getContextPath()+"/");%>
<script type="text/javascript" src="${basePath}js/jquery.js"></script>
 <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" > </script>
 <script>
		$(function(){
			$("#btn_1").click(function(){
				
				alert("go");
				
				
			});
			
			
			
		});
 </script>
      
</head>
<body>
		<h1>timestamp:${share.timestamp}</h1>
		<h1>nonceStr:${share.nonceStr}</h1>
		<h1>appId:${share.appId}</h1>
		<h1>signature:${share.signature}</h1>
			<button id="btn_1">分享到朋友圈</button>
</body>
</html>