<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<% pageContext.setAttribute("basePath", request.getContextPath()+"/") ;%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="${basePath}resource/id" method="GET">
		用户名<input type="text" name="id"/>
     	提交：<input type="submit" name="submit"/>
	</form>  
	
</body>
</html>