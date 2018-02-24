<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath(); %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>高校排课系统</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/plugins/bootstrap/css/bootstrap-select.min.css" rel="stylesheet">
	<script src="<%=path %>/plugins/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap-select.min.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/defaults-zh_CN.min.js"></script>
	<script src="<%=path %>/js/common/common.js"></script>
	<style type="text/css">
	   body{
	      background: #c4e3f3;
	   }
	</style>
</head>
<body>
<form class="form-horizontal" id="myform" name="myform" action="#" method="post">
    <div class="form-group" style="margin-top: 80px;margin-left: 190px;">
        <label for="tname" class="col-sm-2 control-label">教师姓名:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="loginName" name="loginName" value="${tmap.tname }" readonly>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label for="username" class="col-sm-2 control-label">教师编号(用户名):</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="username" name="username" value="${tmap.tnumber }" readonly>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label for="password" class="col-sm-2 control-label">密码:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码">
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label for="password1" class="col-sm-2 control-label">确认密码:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="password1" name="password1" placeholder="请确认密码">
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">对应菜单：</label>
        <div class="col-sm-3">
            <select id="loginMenu" name="loginMenu" class="selectpicker show-tick form-control" multiple data-size="5" data-live-search="false">
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="addBtn" class="btn btn-info">注册</button>
            <button type="button" id="backBtn" class="btn btn-info">返回</button>
        </div>
    </div>
    <input type="hidden" id="tid" name="tid" value="${tmap.tid }">
    <input type="hidden" id="loginUser" name="loginUser" value="${tmap.role }">
</form>
</body>
<script src="<%=path %>/js/xtgl/singupForT.js"></script>
</html>