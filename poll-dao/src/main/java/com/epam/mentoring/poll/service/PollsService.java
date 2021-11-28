package com.epam.mentoring.poll.service;

import com.epam.mentoring.poll.dbConnection.DatabaseConnectionFactory;
import com.epam.mentoring.poll.domain.Poll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PollsService {

    private final Connection connection = DatabaseConnectionFactory.getInstance().getConnection("");

    public PollsService() throws SQLException {
    }

    public List<Poll> findAll() {
        List<Poll> polls = new ArrayList<>();
        String query = "SELECT * FROM poll";
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Poll pollFromResultSet = createFromResultSet(resultSet);
                polls.add(pollFromResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polls;
    }

    public Poll findById(long id) {
        String query = "SELECT * FROM poll where id=" + id;
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Poll createFromResultSet(ResultSet resultSet) throws SQLException {
        Poll poll = new Poll();
        poll.setId(resultSet.getInt("id"));
        poll.setName(resultSet.getString("name"));
        poll.setDescription(resultSet.getString("description"));
        return poll;
    }
}
