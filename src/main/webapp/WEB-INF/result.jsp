<%@ page language="java" import="com.cmcc.bean.User"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java"  import="com.cmcc.bean.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<% pageContext.setAttribute("basePath", request.getContextPath()+"/");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示从接口获得的数据</title>

</head>
<body>
        <h1>这里显示从接口获取的数据</h1>
		<h3>status,${result.status}</h3>
		<h3>message,${result.message}</h3>
		<h3>openId：${result.openid}</h3>
		<h3>nickname,${result.nickname}</h3>
		<h3>headiImgUrl,${result.headimgurl}</h3>
		<h3>手机号码,${result.telnum}</h3>
		<h3>订阅时间,${result.subscribeTime}</h3><br/>
		<h2>测试是否能够完成分享功能</h2><br/>
		<a href="${basePath}secwechat/redirect2share">跳转到分享页面</a>
</body>
</html>