package com.epam.mentoring.poll.service;

import com.epam.mentoring.poll.dbConnection.DatabaseConnectionFactory;
import com.epam.mentoring.poll.domain.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsService {

    private final Connection connection = DatabaseConnectionFactory.getInstance().getConnection("");

    public QuestionsService() throws SQLException {
    }

    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question";
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question questionFromResultSet = createFromResultSet(resultSet);
                questions.add(questionFromResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public Question findById(long id) {
        String query = "SELECT * FROM question where id=" + id;
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

    public List<Question> findByPollId(long id) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question where poll_id=" + id;
        try {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                questions.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private Question createFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt("id"));
        question.setText(resultSet.getString("text"));
        return question;
    }
}
