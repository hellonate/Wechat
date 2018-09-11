<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<%pageContext.setAttribute("basePath", request.getContextPath()+"/");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>aa</h1>
    <form action="${basePath}es/search" method="GET">
		用户名<input type="text" name="username"/>
     	提交：<input type="submit" name="submit"/>
	</form>
	
</body>
</html>