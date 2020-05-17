<%@ page import="objects.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    if (request.getAttribute("cars") == null) {
        response.sendRedirect("period");
        return;
    }
    System.out.println(request.getAttribute("cars"));
    Car[] cars = (Car[]) request.getAttribute("cars");
    int sum = 0;
    for (Car car : cars) {
        sum += car.getPrice();
    }
%>
<head>
</head>
<body>
<table>

    <%for (Car car : cars) { %>
    <tr>
        <td><%=car%>
        </td>

        <td><%=car.getPrice()%>
        </td>
    </tr>
    <%}%>
    <tr>
        <td>finally
        </td>

        <td><%=sum%>
        </td>
    </tr>

</table>
</body>
</html>
