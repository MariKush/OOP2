<%--
  Created by IntelliJ IDEA.
  User: blagi
  Date: 21.03.2020
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%
    if (request.getAttribute("CarStyles") == null) {
        response.sendRedirect("orderCar");
        return;
    }
    String[] carStyles = (String[]) request.getAttribute("CarStyles");

%>
<head>
    <title>Title</title>
</head>
<body>

<form class="modal-content animate" action="../searchCar">

    <label for="startDay">startDay:</label>
    <input type="date" id="startDay" name="startDay" required><!--min="2018-01-01" max="2018-12-21"-->
    <label for="endDay">endDay:</label>
    <input type="date" id="endDay" name="endDay" required><!--min="2018-01-01" max="2018-12-21"-->

    <div id="carTypes">
        <%for (int i = 0; i < carStyles.length; i++) { %>
        <input type="checkbox" id="carType<%=i%>" name="carType" value="<%=carStyles[i]%>" checked>
        <label for="carType<%=i%>"><%=carStyles[i]%>
        </label><br>
        <%}%>
    </div>

    <div id="prices">
        <label for="minValue">min</label>
        <input type="number" id="minValue" name="minValue" min=100 max=100000 required value=100>
        <label for="maxValue">max</label>
        <input type="number" id="maxValue" name="maxValue" min=100 max=100000 required value=100000>
    </div>


    <input type="submit" value="Search">
</form>
</body>
</html>
