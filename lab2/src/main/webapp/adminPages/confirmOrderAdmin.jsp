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
    <title>Confirm order by admin</title>
</head>
<body>
<% for (int i = 0; i < orders.length; i++) {%>
<div>
    <h5>Order #<%=orders[i].getId()%></h5>
    <h5>Car: <%=orders[i].getCar()%></h5>
    <h5>Name:  <%=orders[i].getName()%></h5>
    <h5>Surname:  <%=orders[i].getSurname()%></h5>
    <h5>PassportID:  <%=orders[i].getPassportID()%></h5>
    <h5>Credit card number: <%=orders[i].getCreditCard()%></h5>
    <h5>Mobile number:  <%=orders[i].getMobileNum()%></h5>
    <form action="confirmOrderAdmin?id=<%=orders[i].getId()%>&type=confirm" method="post">
        <input type="submit" value="Confirm">
    </form>
    <form action="confirmOrderAdmin?id=<%=orders[i].getId()%>&type=refuse" method="post">
        <div>
            <label for="Comment">Reason for refusal</label>
            <input type="text" id="Comment" name="Comment"/>
        </div>
        <input type="submit" value="Refuse">
    </form>

</div>
<% }%>
<% if (orders.length == 0) {%>
    <p>There are no orders to confirm</p>
<% }%>
<a href="adminPage"> <p>Back to adminPage</p> </a>

</body>
</html>
