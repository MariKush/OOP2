<%@ page import="objects.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    if (request.getAttribute("orders") == null) {
        response.sendRedirect("confirmOrderAdmin");
        return;
    }
    Order[] orders = (Order[]) request.getAttribute("orders");
%>
<head>
    <title>Title</title>
</head>
<body>
<% for (int i = 0; i < orders.length; i++) {%>
<div>

    <h5> <%=orders[i].getCar()%></h5>
    <h5> <%=orders[i].getName()%></h5>
    <h5> <%=orders[i].getSurname()%></h5>
    <h5> <%=orders[i].getPassportID()%></h5>
    <h5> <%=orders[i].getCreditCard()%></h5>
    <h5> <%=orders[i].getMobileNum()%></h5>
    <form action="confirmOrderAdmin?id=<%=orders[i].getId()%>&type=confirm" method="post">
        <input type="submit" value="Confirm">
    </form>
    <form action="confirmOrderAdmin?id=<%=orders[i].getId()%>&type=refuse" method="post">
        <div>
            <label for="Comment">Credit Card</label>
            <input type="text" id="Comment" name="Comment"/>
        </div>
        <input type="submit" value="Refuse">
    </form>

</div>
<% }%>

</body>
</html>
