<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car brand</title>
</head>
<body>

<form class="modal-content animate" action="../addCarBrand" method="post">
    <label for="carBrand"><b>carBrand</b></label>
    <input type="text" name="carBrand" id="carBrand" placeholder="Enter carBrand" required>

    <input type="submit" value="Add brand">
    <a href="adminPage"> <p>Back to adminPage</p> </a>
</form>

</body>

<jsp:include page="../alert.jsp"/>
</html>
