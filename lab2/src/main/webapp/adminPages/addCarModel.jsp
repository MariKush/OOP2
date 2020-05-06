<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    if ( request.getAttribute("CarBrands") == null) {
        response.sendRedirect("addCarModel");
        return;
    }
    String[] carBrands = (String[]) request.getAttribute("CarBrands");
%>
<head>
    <title>Add car model</title>
</head>
<body>

<form action="../addCarModel" method="post">

    <label for="carBrand"></label>
    <select id="carBrand" name="carBrand">
        <% for (String carBrand : carBrands) {%>
        <option value=<%=carBrand%>><%=carBrand%></option>
        <%}%>
    </select><br>


    <label for="carModel"><b>carModel</b></label>
    <input type="text" name="carModel" id="carModel" placeholder="Enter carModel" required>

    <input type="submit" id="submit" value="add model">
    <a href="adminPage"> <p>Back to adminPage</p> </a>
</form>

</body>

<jsp:include page="../alert.jsp"/>
</html>
