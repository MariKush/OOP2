<%--
  Created by IntelliJ IDEA.
  User: blagi
  Date: 22.03.2020
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>
    <%if (request.getSession().getAttribute("Error")!=null){%>
    window.onload= function (){
        alert("<%= ((String)request.getSession().getAttribute("Error"))%>");
    };
    <%
    request.getSession().setAttribute("Error", null);
    }%>
</script>
</html>
