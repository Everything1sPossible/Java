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
	<script src="<%=path %>/plugins/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path %>/js/common/common.js"></script>
	<script src="<%=path %>/js/xtgl/teachergl.js"></script>
	<style type="text/css">
	   body{
	      background: #c4e3f3;
	   }
	</style>
</head>
<body>
<form id="myform" name="myform" class="form-inline" action="#" method="post">
    <div class="form-group" style="margin-top: 80px;margin-left: 205px;">
        <label for="tname">教师姓名:</label>
        <input type="text" class="form-control" id="qtname" placeholder="请输入教师姓名">
    </div>
    <div class="form-group" style="margin-top: 80px;">
        <label for="tnumber">教师编号:</label>
        <input type="text" class="form-control" id="qtnumber" placeholder="请输入教师编号">
    </div>
    <button type="button" class="btn btn-info queryBtn" style="margin-top: 80px;">查询</button>
    <button type="button" class="btn btn-info addBtn" style="margin-top: 80px;">添加</button>
    <button type="button" class="btn btn-danger delBtn" disabled="disabled" style="margin-top: 80px;">删除</button>
    <table class="table table-hover" style="width: 70%;" align="center">
        <caption style="text-align: center"><h4>教师列表</h4></caption>
        <thead>
              <tr class="success">
                <td><input type="checkbox" id="allcheck" name="allcheck">全选</td>
                <td>序号</td>
                <td>教师姓名</td>
                <td>教师编号</td>
                <td>所属角色</td>
                <td>任教课程</td>
                <td>所属院系</td>
                <td>操作</td>
              </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <!-- 分页 -->
    <div aria-label="Page navigation" id="pageShow" style="width: 70%;margin-left: 205px;">
    </div>
</form>
</body>

</html>