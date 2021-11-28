<%@ page import="java.util.List" %>
<%@ page import="com.epam.mentoring.poll.domain.Poll" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Poll</title>
</head>
<body>
<%List<Poll> all = (List<Poll>) request.getAttribute("all");%>
<h1>Выберите Опрос</h1>
<ul>
    <% for (Poll poll : all) {%>
    <li><a href="/questions?id=<%=poll.getId()%>"><%=poll.getName()%>
    </a>
    </li>
    <% }%>
</ul>
</body>
</html>