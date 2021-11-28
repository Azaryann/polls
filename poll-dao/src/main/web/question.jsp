<%@ page import="com.epam.mentoring.poll.domain.Question" %>
<%@ page import="com.epam.mentoring.poll.domain.Poll" %>
<%@ page import="java.util.List" %>

<%@ page import="com.epam.mentoring.poll.domain.Answer" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Questions</title>
</head>
<body>
<%List<Question> questions = (List<Question>) request.getAttribute("questions");%>
<%Poll poll = (Poll) request.getAttribute("poll");%>
<h1><%=poll.getName()%>
</h1>
<h3><%=poll.getDescription()%>
</h3>
<form action="/answers">
    <input type="hidden" name="pollId" value="<%=poll.getId()%>">
    <%for (Question question : questions) { %>
    <h4><%=question.getText()%>
    </h4>
    <%for (Answer answer : question.getAnswers()) {%>
    <input type="radio" id="radio" name="question<%=question.getId()%>" value="<%=answer.getWeight()%>">
    <label for="radio"><%=answer.getText()%>
    </label><br>
    <br>
    <%}%>
    <%}%>
    <input type="submit" value="Submit">
</form>
</body>
</html>