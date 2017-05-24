<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简易考试操作界面</title>
<script type="text/javascript">
 appConfig={userName:'${sessionScope.user.name}'}
</script>
<link rel="stylesheet" charset="utf-8" type="text/css" href="<%=basePath %>/css/css/ext-all.css" />
 <script type="text/javascript" src="<%=basePath %>/extjs/bootstrap.js"></script>
 <script type="text/javascript" src="<%=basePath %>/extjs/ext-lang-zh_CN.js"></script>
 <script type="text/javascript" src="<%=basePath %>/myApp/app.js"></script>
</head>
<body>

</body>
</html>