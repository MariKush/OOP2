<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Period</title>
</head>
<body>
<form class="modal-content animate" action="../statistics" method="get">

    <p>Select the period for which you want to get a report</p>
    <label for="startDay">startDay:</label>
    <input type="date" id="startDay" name="startDay" required><!--min="2018-01-01" max="2018-12-21"-->
    <label for="endDay">endDay:</label>
    <input type="date" id="endDay" name="endDay" required><!--min="2018-01-01" max="2018-12-21"-->

    <input type="submit" value="Show">

    <a href="adminPage"><p>Back to adminPage</p></a>
</form>
</body>
</html>
