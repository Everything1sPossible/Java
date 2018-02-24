<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
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
	<script src="<%=path %>/js/main/login.js"></script>
	<style type="text/css">
	   body{
	      background: #c4e3f3;
	   }
	</style>
</head>
<body>
<div class="container" style="width: 35%;margin-top: 60px; ">
    <form class="form-signin" id="myform" name="myform" method="post" action="<%=path %>/login.html">
        <h2 class="form-signin-heading">欢迎登陆</h2>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
            <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名" required autofocus>
        </div>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
            <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" required>
        </div>
        <div class="row">
            <div class="col-md-8">
                <input type="text" id="kaptchaCode" name="kaptchaCode" class="form-control" placeholder="请输入验证码" required>
            </div>
            <img src="Kaptcha.jpg" id="kaptcha" alt="验证码">
            <a href="javascript:changeImg()">换一张</a>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 下次自动登录
            </label>
        </div>
        <div id="msgDiv" style="display: none;color: red;">
        </div>
        <button id="button" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
    </form>
    <div class="navbar navbar-fixed-bottom" style="text-align: center;margin-bottom: -20px" role="navigation">
        Copyright © 2017 高校排课系统 GXPK　
    </div>
</div> <!-- /container -->
</body>
</html>