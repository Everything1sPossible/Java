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
        <label for="menuName" class="col-sm-2 control-label">菜单名称:</label>
        <div class="col-sm-3">
            <input type="text" id="menuName" name="menuName" class="form-control" value="${menuMap.menuname }" placeholder="请输入菜单名称">
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label for="menuUrl" class="col-sm-2 control-label">菜单url:</label>
        <div class="col-sm-3">
            <input type="text" id="menuUrl" name="menuUrl" class="form-control" value="${menuMap.menuurl}" placeholder="请输入菜单url">
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">菜单级别:</label>
        <div class="col-sm-3 levelradio">
            <input type="radio" id="menuLevel1" name="menuLevel" ${menuMap.menulevel==1?'checked':'' } value="1">一级
            <input type="radio" id="menuLevel2" name="menuLevel" ${menuMap.menulevel==2?'checked':'' } value="2">二级
            <input type="hidden" id="hideLevel" value="${menuMap.menulevel }">
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">上一级菜单：</label>
        <div class="col-sm-3">
            <select id="menuFather" name="menuFather" class="selectpicker show-tick form-control" data-size="5" data-live-search="false">
              <c:choose>
                <c:when test="${menuMap.menufather==0 }">
                   <option value="0">一级菜单</option>
                </c:when>
                <c:otherwise>
                   <option value="${menuMap.menufather }">${menuMap.menufathername }</option>
                </c:otherwise>
              </c:choose>
            </select>
        </div>
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <label class="col-sm-2 control-label">菜单状态:</label>
        <div class="col-sm-3">
            <input type="radio" id="menuState0" name="menuState" ${menuMap.menustate==0?'checked':'' } value="0">弃用
            <input type="radio" id="menuState1" name="menuState" ${menuMap.menustate==1?'checked':'' } value="1">使用
        </div>
        
    </div>
    <div class="form-group" style="margin-left: 190px;">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" id="${empty param.menuId?'addBtn':'updBtn' }" class="btn btn-info updBtn">${empty param.menuId?'添加':'修改' }</button>
            <button type="button" id="backBtn" class="btn btn-info addBtn">返回</button>
        </div>
    </div>
    <input type="hidden" id="menuId" name="menuId" value="${menuMap.menuid }">
</form>
</body>
<script src="<%=path %>/js/xtgl/cdgl_update.js"></script>
</html>