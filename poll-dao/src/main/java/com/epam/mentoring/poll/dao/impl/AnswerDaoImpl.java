package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.AnswerDao;
import com.epam.mentoring.poll.domain.Answer;
import com.epam.mentoring.poll.service.AnswersService;

import java.sql.SQLException;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private final AnswersService answersService = new AnswersService();

    public AnswerDaoImpl() throws SQLException {
    }

    @Override
    public List<Answer> findAll() {
        return answersService.findAll();
    }

    @Override
    public Answer findById(long id) {
        return answersService.findById(id);
    }

    @Override
    public List<Answer> findByQuestionId(long questionId) {
        return answersService.findByQuestionId(questionId);
    }
}
