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
    <form action="confirmReturn?id=<%=orders[i].getId()%>&type=correct" method="post">
        <input type="submit" value="Confirm correct">
    </form>
    <form action="confirmReturn?id=<%=orders[i].getId()%>&type=damaged" method="post">
        <div>
            <label for="Comment">Credit Card</label>
            <input type="text" id="Comment" name="Comment"/>
        </div>
        <div>
            <label for="price"><b>price</b></label>
            <input type="number" name="price" id="price" placeholder="Enter price" required>
        </div>
        <input type="submit" value="Confirm damage">
    </form>

</div>
<% }%>

</body>
</html>
