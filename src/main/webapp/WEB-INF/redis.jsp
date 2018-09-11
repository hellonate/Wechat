<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
    <%pageContext.setAttribute("basePath", request.getContextPath()+"/");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${basePath}secwechat/redirect2share">跳转到分享页面</a>
</body>
</html>