<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form class="modal-content animate" action="../addCarBrand" method="post">
    <label for="carBrand"><b>carBrand</b></label>
    <input type="text" name="carBrand" id="carBrand" placeholder="Enter carBrand" required>

    <input type="submit" value="Add brand">
</form>

</body>

<jsp:include page="../alert.jsp"/>
</html>
