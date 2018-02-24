<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%String path = request.getContextPath(); %>
<%
  //获取登录信息
  Map<String,Object> resultMap = (Map<String,Object>)session.getAttribute("userMap");
  String loginName = resultMap.get("loginname").toString();
  //获取一级菜单List
  List<Map<String,Object>> FMenuList = (List<Map<String,Object>>)request.getAttribute("FMenuList");
  //获取二级菜单
  Map<String,String[][]> menu2Map = (Map<String,String[][]>)request.getAttribute("menu2Map");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>欢迎进入高校排课系统!</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path %>/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=path %>/plugins/jquery/jquery-1.11.3.js"></script>
	<script src="<%=path %>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=path %>/js/common/showTime.js"></script>
	<script src="<%=path %>/js/main/top.js"></script>
	<style>
        body{
            background: #c4e3f3;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-header">
        <p class="navbar-text" style="color: red">欢迎[&nbsp;<%=loginName %>&nbsp;]登陆</p>
        <p class="navbar-text" id="showTime"></p>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-left">
          <%if(FMenuList != null){
        	  for(int i=0;i<FMenuList.size();i++){
        		  String menu1Id = FMenuList.get(i).get("menuid").toString();
        		  String menu1Name = FMenuList.get(i).get("menuname").toString();
        		  String menu1Url = FMenuList.get(i).get("menuurl").toString();%>
        		  <li class="dropdown">
	                <a href="<%=menu1Url %>" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=menu1Name %> <span class="caret"></span></a>
	                <ul class="dropdown-menu">
	                    <%
	                       if(menu1Id != null){
	                    	   String[][] menu2 = menu2Map.get(menu1Id);
		                    	  if(menu2 != null){
		                    		  for(int j=0;j<menu2.length;j++){
		                    			  %>
				                    	  <li><a href="<%=menu2[j][2] %>" target="view"><%=menu2[j][1] %></a></li> 
	                      <%         }
	                    	     }
	                       }
	                    %>
	                </ul>
              </li>
        	  <%}
            }%>
            <li><a href="<%=path %>/loginOut.html" id="logOut" class="ghost-button ghost-button-blue">退出系统</a></li>
        </ul>
    </div>
</nav>
<iframe name="view" width="100%" height="660" frameborder="0">
</iframe>
</body>
</html>