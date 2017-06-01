<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath=request.getContextPath(); 
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简易考试操作界面</title>
<script type="text/javascript">
var contextPath="<%= basePath %>";
var appConfig={userName:'${sessionScope.user.name}'}
var WebServer=location.hostname+":"+location.port;
var pointUrl="webSocketServer";
 var webPath='<%=basePath%>/'+pointUrl;
 var MozWebSocketUrl=pointUrl;
 var WinsocketUrl="ws://"+WebServer+webPath;
 var SockJSUrl="http://"+WebServer+"<%=basePath%>/sockjs/"+pointUrl;
 var userType=${sessionScope.user.type};
 
</script>
<link rel="stylesheet" charset="utf-8" type="text/css" href="<%=basePath %>/css/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/notification.css" />
 <script type="text/javascript" src="<%=basePath %>/extjs/bootstrap.js"></script>
 <script type="text/javascript" src="<%=basePath %>/extjs/ext-lang-zh_CN.js"></script>
  <script type="text/javascript" src="<%=basePath %>/extjs/sockjs-0.3.min.js"></script>
 <script type="text/javascript" src="<%=basePath %>/myApp/app.js"></script>
</head>
<body>

</body>
</html>