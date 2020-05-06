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
