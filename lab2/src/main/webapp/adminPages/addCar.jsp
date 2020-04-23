<%@ page import="objects.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    if (request.getAttribute("CarStyles") == null || request.getAttribute("CarModels") == null) {
        response.sendRedirect("addCar");
        return;
    }
    String[] carStyles = (String[]) request.getAttribute("CarStyles");
    Model[] carModels = (Model[]) request.getAttribute("CarModels");
%>
<head>
    <title>Title</title>
</head>
<body>

<form action="../addCar" method="post">

    <label for="carModel"></label>
    <select id="carModel" name="carModel">
        <% for (int i = 0; i < carModels.length; i++) {%>
        <option value="<%=carModels[i].getModelID()%>"> <%=carModels[i].toString()%> </option>
        <%}%>
    </select><br>

    <label for="carStyle"></label>
    <select id="carStyle" name="carStyle">
        <% for (String carStyle : carStyles) {%>
        <option value=<%=carStyle%>><%=carStyle%>
        </option>
        <%}%>
    </select><br>

    <label for="yearProduction"><b>yearProduction</b></label>
    <input type="number" name="yearProduction" id="yearProduction" placeholder="Enter yearProduction" required>

    <label for="price"><b>price</b></label>
    <input type="number" name="price" id="price" placeholder="Enter price" required>

    <input type="submit" id="submit" value="add car">
</form>

</body>
<jsp:include page="../alert.jsp"/>
</html>
