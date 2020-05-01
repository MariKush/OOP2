<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car style</title>
</head>
<body>

<form class="modal-content animate" action="../addCarStyle" method="post">
    <label for="carStyle"><b>carStyle</b></label>
    <input type="text" name="carStyle" id="carStyle" placeholder="Enter carStyle" required>

    <input type="submit" value="Add style">
</form>

</body>
<jsp:include page="../alert.jsp"/>
</html>
