package com.epam.mentoring.poll.service;

import com.epam.mentoring.poll.dbConnection.DatabaseConnectionFactory;
import com.epam.mentoring.poll.domain.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultsService {

    private final Connection connection = DatabaseConnectionFactory.getInstance().getConnection("");

    public ResultsService() throws SQLException {
    }

    public List<Result> findAll() {
        List<Result> results = new ArrayList<>();
        String query = "SELECT * FROM result";
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Result findById(long id) {
        String query = "SELECT * FROM result where id=" + id;
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

    public List<Result> findByPollId(long id) {
        List<Result> results = new ArrayList<>();
        String query = "SELECT * FROM result where poll_id=" + id;
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Result findByScore(long pollId, int score) {
        String query = "SELECT * FROM result where poll_id=? and min_score <=? and max_score>=?";
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (int) pollId);
            statement.setInt(2, score);
            statement.setInt(3, score);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Result createFromResultSet(ResultSet resultSet) throws SQLException {
        Result result = new Result();
        result.setId(resultSet.getInt("id"));
        result.setExplanation(resultSet.getString("explanation"));
        result.setMinScore(resultSet.getInt("minScore"));
        result.setMaxScore(resultSet.getInt("maxScore"));
        return result;
    }
}
