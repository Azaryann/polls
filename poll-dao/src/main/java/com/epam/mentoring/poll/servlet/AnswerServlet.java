package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.dao.ResultDao;
import com.epam.mentoring.poll.dao.impl.QuestionDaoImpl;
import com.epam.mentoring.poll.dao.impl.ResultDaoImpl;
import com.epam.mentoring.poll.domain.Question;
import com.epam.mentoring.poll.domain.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/answers")
public class AnswerServlet extends HttpServlet {

    private final QuestionDao questionDao = new QuestionDaoImpl();
    private final ResultDao resultDao = new ResultDaoImpl();

    public AnswerServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("pollId");
        int pollId = Integer.parseInt(id);
        List<Question> questions = questionDao.findAll();
        int tmp = 0, score;
        for (Question question : questions) {
            String weight = req.getParameter("question" + question.getId());
            tmp += Integer.parseInt(weight);
        }
        score = tmp / questions.size();
        Result byScore = resultDao.findByScore(pollId, score);
        req.setAttribute("result", byScore);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

}
