package com.epam.mentoring.poll.dao.impl;

import com.epam.mentoring.poll.dao.Dao;
import com.epam.mentoring.poll.domain.Poll;
import com.epam.mentoring.poll.service.PollsService;

import java.sql.SQLException;
import java.util.List;

public class PollDaoImpl implements Dao<Poll> {

    private final PollsService pollsService = new PollsService();

    public PollDaoImpl() throws SQLException {
    }

    @Override
    public List<Poll> findAll() {
        return pollsService.findAll();
    }

    @Override
    public Poll findById(long id) {
        return pollsService.findById(id);
    }
}
