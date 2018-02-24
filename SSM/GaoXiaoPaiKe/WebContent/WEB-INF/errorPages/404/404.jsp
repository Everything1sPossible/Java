<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath(); 
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>404</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=path %>/plugins/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap.min.js"></script>
    <style>
        body{
            background: #9acfea;
        }
    </style>
</head>
<body>
<div class="container" style="text-align: center;margin-top: 120px;">
    <img src="<%=path %>/errorPages/404/image/404.png" alt="404"/>
    &nbsp;
    <h3>您访问的资源不存在</h3>
    &nbsp;
    <p>
        <a id="error" href="javascript:void(0)"><img src="<%=path %>/errorPages/404/image/404_to_index.png"/></a>
    </p>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("#error").click(function(){
		parent.window.location.href="<%=basePath%>/login.html";
	});
});
</script>
</html>