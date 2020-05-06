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
    <title>Confirm return</title>
</head>
<body>
<% for (int i = 0; i < orders.length; i++) {%>
<div>
    <h5>Order #<%=orders[i].getId()%></h5>
    <h5>Car: <%=orders[i].getCar()%></h5>
    <h5>Name:  <%=orders[i].getName()%></h5>
    <h5>Surname:  <%=orders[i].getSurname()%></h5>
    <h5>PassportID: <%=orders[i].getPassportID()%></h5>
    <h5>Credit card number:  <%=orders[i].getCreditCard()%></h5>
    <h5>Mobile number:  <%=orders[i].getMobileNum()%></h5>
    <form action="confirmReturn?id=<%=orders[i].getId()%>&type=correct" method="post">
        <input type="submit" value="Confirm return without damage">
    </form>
    <form action="confirmReturn?id=<%=orders[i].getId()%>&type=damaged" method="post">
        <div>
            <label for="Comment"><b>Type of damage:</b></label>
            <input type="text" id="Comment" name="Comment"/>
        </div>
        <div>
            <label for="price"><b>The cost of damage:</b></label>
            <input type="number" name="price" id="price" placeholder="Enter price" required>
        </div>
        <input type="submit" value="Confirm return with damage">
    </form>

</div>
<% }%>
<% if (orders.length == 0) {%>
    <p>There are no orders to confirm return</p>
<% }%>
<a href="adminPage"> <p>Back to adminPage</p> </a>

</body>
</html>
