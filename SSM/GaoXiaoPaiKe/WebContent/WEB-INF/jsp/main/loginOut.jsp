<%@ page language="java" pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<%session.invalidate(); %>


<script>
  parent.window.location="<%=path%>/login.html";
</script>
