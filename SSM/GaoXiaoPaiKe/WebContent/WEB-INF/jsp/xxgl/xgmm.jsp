<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%String path = request.getContextPath(); %>
<%
   Map<String,Object> userMap = (Map<String,Object>)session.getAttribute("userMap");
   String loginId = userMap.get("loginid").toString();
   String loginName = userMap.get("loginname").toString();
   String username = userMap.get("username").toString();
   String password = userMap.get("password").toString();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>高校排课系统</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=path %>/plugins/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path %>/js/xxgl/xgmm.js"></script>
	<script src="<%=path %>/js/common/common.js"></script>
	<style type="text/css">
	   body{
	      background: #c4e3f3;
	   }
	</style>
</head>
<body>
<div class="container" style="margin-top: 100px; ">
	<form class="form-horizontal" id="myform" name="myform" action="#" method="post">
	    <div class="form-group">
	        <label for="password" class="col-sm-2 control-label">原密码:</label>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" id="password" name="password" value="<%=password %>" readonly>
	        </div>
	    </div>
	    <div class="form-group">
	        <label for="password" class="col-sm-2 control-label">新密码:</label>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" id="Newpassword" name="Newpassword">
	        </div>
	    </div>
	    <div class="form-group">
	        <label for="password" class="col-sm-2 control-label">确认密码:</label>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" id="Newpassword2" name="Newpassword2">
	        </div>
	    </div>
	    <div class="form-group">
	        <div class="col-sm-offset-2 col-sm-5">
	            <button type="button" id="updateBtn" class="btn btn-info">修改</button>
	            <input type="hidden" id="loginId" name="loginId" value="<%=loginId%>">
	            <button type="button" id="backBtn" class="btn btn-info">返回</button>
	        </div>
	    </div>
	</form>
</div>
</body>
</html>