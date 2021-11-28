package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.AnswerDao;
import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.dao.impl.AnswerDaoImpl;
import com.epam.mentoring.poll.dao.impl.PollDaoImpl;
import com.epam.mentoring.poll.dao.impl.QuestionDaoImpl;
import com.epam.mentoring.poll.domain.Poll;
import com.epam.mentoring.poll.domain.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/questions")
public class QuestionsServlet extends HttpServlet {

    private final QuestionDao questionDao = new QuestionDaoImpl();
    private final AnswerDao answerDao = new AnswerDaoImpl();
    private final PollDaoImpl pollDao = new PollDaoImpl();

    public QuestionsServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int pollId = Integer.parseInt(id);
        Poll poll = pollDao.findById(pollId);
        if (poll != null) {
            List<Question> byPollId = questionDao.findByPollId(pollId);
            byPollId.forEach(e -> e.setAnswers(answerDao.findByQuestionId(e.getId())));
            req.setAttribute("poll", poll);
            req.setAttribute("questions", byPollId);
            req.getRequestDispatcher("question.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Requested Poll wasn't found, please try again");
            resp.sendRedirect("/");
        }
    }
}
