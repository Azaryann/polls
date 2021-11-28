package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.impl.PollDaoImpl;
import com.epam.mentoring.poll.domain.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    private final PollDaoImpl pollDao = new PollDaoImpl();

    public HomeServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Poll> all = pollDao.findAll();
        request.setAttribute("all", all);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
