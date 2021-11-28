package com.epam.mentoring.poll.service;

import com.epam.mentoring.poll.dbConnection.DatabaseConnectionFactory;
import com.epam.mentoring.poll.domain.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswersService {

    private final Connection connection = DatabaseConnectionFactory.getInstance().getConnection("");

    public AnswersService() throws SQLException {
    }

    public List<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answer";
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public Answer findById(long id) {
        String query = "SELECT * FROM answer where id=" + id;
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

    public List<Answer> findByQuestionId(long questionId) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answer where question_id=" + questionId;
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private Answer createFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getInt("id"));
        answer.setText(resultSet.getString("text"));
        answer.setWeight(resultSet.getInt("weight"));
        return answer;
    }
}
