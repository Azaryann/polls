<%@ page import="com.epam.mentoring.poll.domain.Result" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<%Result result = (Result) request.getAttribute("result");%>
<h1>Результаты опроса</h1>
<h3>
    <%if (result != null) {%>
    <%=result.getExplanation()%>
    <%} else {%>
    <p>Result</p>
    <%}%>
</h3>

<button>К списку опросов</button>
</body>
</html>
