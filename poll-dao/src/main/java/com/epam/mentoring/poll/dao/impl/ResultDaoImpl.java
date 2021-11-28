package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.service.ResultsService;
import com.epam.mentoring.poll.dao.ResultDao;
import com.epam.mentoring.poll.domain.Result;

import java.sql.SQLException;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    private final ResultsService resultsService = new ResultsService();

    public ResultDaoImpl() throws SQLException {
    }

    @Override
    public List<Result> findAll() {
        return resultsService.findAll();
    }

    @Override
    public Result findById(long id) {
        return resultsService.findById(id);
    }

    @Override
    public List<Result> findByPollId(long pollId) {
        return resultsService.findByPollId(pollId);
    }

    @Override
    public Result findByScore(long pollId, int score) {
        return resultsService.findByScore(pollId, score);
    }
}
