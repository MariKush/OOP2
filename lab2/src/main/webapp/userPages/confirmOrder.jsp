<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    int id = Integer.parseInt(request.getParameter("id"));

%>
<head>
    <title>Confirm order by user</title>
</head>
<body>

<form action="../confirmOrder?id=<%=id%>&startDay=<%=request.getParameter("startDay")%>&endDay=<%=request.getParameter("endDay")%>" method="post">
    <div>
        <label for="Name"><b>Your name</b></label>
        <input type="text" name="Name" id="Name" placeholder="Enter Name" pattern="[A-Za-z]{2,10}" required>
    </div>

    <div>
        <label for="Surname"><b>Your Surname</b></label>
        <input type="text" name="Surname" id="Surname" placeholder="Enter Surname" pattern="[A-Za-z]{2,20}" required>
    </div>

    <div>
        <label for="PassportID"><b>Your PassportID</b></label>
        <input type="text" name="PassportID" id="PassportID" placeholder="Enter PassportID"
               pattern="[0-9]{9}" required>
    </div>

    <div>
        <label for="MobileNum"><b>Your MobileNum</b></label>
        <input type="text" name="MobileNum" id="MobileNum" placeholder="Enter MobileNum"
               pattern="[0-9]{10}" required>
    </div>

    <div>
        <label for="CreditCard"><b>Credit Card</b></label>
        <input type="text" id="CreditCard" name="CreditCard" pattern="[0-9]{16}"/>
    </div>

    <input type="submit" value="Order">
    <a href='..'> <p>return to search results</p></a>
</form>

</body>
</html>
