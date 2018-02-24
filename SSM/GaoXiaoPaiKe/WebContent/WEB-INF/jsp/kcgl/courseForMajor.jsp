<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
        <label for="department" class="col-sm-2 control-label">院系:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="department" name="department" value="计算机工程学院" readonly>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">专业：</label>
        <div class="col-sm-3">
            <select id="major" name="major" class="selectpicker show-tick form-control" data-size="10" data-live-search="true">
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">年级：</label>
        <div class="col-sm-3">
            <select id="grade" name="grade" class="selectpicker show-tick form-control" data-size="5" data-live-search="true">
                <option value="1" selected>大一</option>
                <option value="2">大二</option>
                <option value="3">大三</option>
                <option value="4">大四</option>
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">课程：</label>
        <div class="col-sm-3">
            <select id="course" name="course" class="selectpicker show-tick form-control" multiple data-size="10" data-live-search="true">
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="addBtn" class="btn btn-info">调整</button>
            <button type="button" id="refreshBtn" class="btn btn-info">重置</button>
        </div>
    </div>
</form>
</body>
<script src="<%=path %>/js/kcgl/courseForMajor.js"></script>
</html>