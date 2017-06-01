<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=basePath %>/user/login" method="post">
请输入用户名<input type="text" name="userName" placeholder="请输入长学号" value="123123"/><br>
请输入密码<input type="text" name="pwd" placeholder="默认密码为长学号" value="123123"/><br>
<input type="submit" value="登录">
${requestScope.errors }
</form>
</body>
</html> 