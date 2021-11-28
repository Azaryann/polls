package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.domain.Question;
import com.epam.mentoring.poll.service.QuestionsService;

import java.sql.SQLException;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final QuestionsService questionsService = new QuestionsService();

    public QuestionDaoImpl() throws SQLException {
    }

    @Override
    public List<Question> findAll() {
        return questionsService.findAll();
    }

    @Override
    public Question findById(long id) {
        return questionsService.findById(id);
    }

    @Override
    public List<Question> findByPollId(long pollId) {
        return questionsService.findByPollId(pollId);
    }
}
