<%@ page import="objects.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    if (request.getAttribute("Cars") == null) {
        response.sendRedirect("searchCar");
        return;
    }
    Car[] cars = (Car[]) request.getAttribute("Cars");

%>
<head>
    <title>Search car</title>
</head>
<body>
<div>
    <a href="searchCar"> search car</a>
</div>
<%for (int i = 0; i < cars.length; i++) { %>
<div>
    <h2><%=cars[i]%></h2>
    <h3><%=cars[i].getPrice()%> </h3>
    <form action="../searchCar?id=<%=cars[i].getId()%>&startDay=<%=request.getParameter("startDay")%>&endDay=<%=request.getParameter("endDay")%>" method="post">
        <input type="submit" value="Order">
    </form>

</div>
<%}%>
<a href='..'> <p>return to search</p></a>
</body>
</html>
