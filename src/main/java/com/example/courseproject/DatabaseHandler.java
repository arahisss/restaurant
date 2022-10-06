package com.example.courseproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseHandler {
    Connection dbConnection;

    public static User currentUser;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(
                "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1971_my_app",
                "std_1971_my_app", "root1234");

        return dbConnection;
    }

    public void assignIdUser() {
        ResultSet result = getUser(currentUser);

        try {
            result.next();
            currentUser.setId(result.getLong("id"));
            currentUser.setId_role(result.getLong("id_role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление записи в таблицу users
    public void signUpUser(User user) {

        String insert = "INSERT INTO users (id_role, login, hash_password) VALUES(?, ?, ?)";

        currentUser = user;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, "1");
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getPassword());

            prSt.executeUpdate();
            assignIdUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTeacherQuote(TeacherQuote teacherQuote) {
        String insert = "INSERT INTO teacher_quotes (id_user, quote, teacher, subject) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, currentUser.getId());
            prSt.setString(2, teacherQuote.getQuote());
            prSt.setString(3, teacherQuote.getTeacher());
            prSt.setString(4, teacherQuote.getSubject());

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteTeacherQuote(TeacherQuote teacherQuote) {
        String delete = "DELETE FROM teacher_quotes WHERE teacher = ? AND subject = ? AND quote = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, teacherQuote.getTeacher());
            prSt.setString(2, teacherQuote.getSubject());
            prSt.setString(3, teacherQuote.getQuote());

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTeacherQuote(TeacherQuote teacherQuote) {
        String update = "UPDATE teacher_quotes SET teacher=?, subject=?, quote = ? WHERE id=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, teacherQuote.getTeacher());
            prSt.setString(2, teacherQuote.getSubject());
            prSt.setString(3, teacherQuote.getQuote());
            prSt.setString(4, String.valueOf(teacherQuote.getId()));


            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateUser() {
        String update = "UPDATE users SET login=?, hash_password=? WHERE id=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, currentUser.getLogin());
            prSt.setString(2, currentUser.getPassword());
            prSt.setString(3, currentUser.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM users WHERE login=? AND hash_password=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resultSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }


    public ArrayList<TeacherQuote> getTeacherQuotes() {

        ResultSet resultSet = null;
        String select = "SELECT * FROM teacher_quotes";
        ArrayList<TeacherQuote> quotes = new ArrayList<>();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();

            while (true) {
                if (!resultSet.next()) break;

                TeacherQuote quote = new TeacherQuote(resultSet.getLong("id"),
                        resultSet.getString("teacher"),
                        resultSet.getString("subject"),
                        resultSet.getString("quote"),
                        resultSet.getString("date").substring(0, 10));

                quotes.add(quote);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return quotes;
    }

    public ArrayList<TeacherQuote> getMyNotes() {

        ResultSet resultSet;
        String select = "SELECT * FROM teacher_quotes WHERE id_user=?";
        ArrayList<TeacherQuote> quotes = new ArrayList<>();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, currentUser.getId());
            resultSet = prSt.executeQuery();

            while (true) {
                if (!resultSet.next()) break;

                TeacherQuote quote = new TeacherQuote(resultSet.getLong("id"),
                        resultSet.getString("teacher"),
                        resultSet.getString("subject"),
                        resultSet.getString("quote"),
                        resultSet.getString("date").substring(0, 10));

                quotes.add(quote);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return quotes;
    }

    public ResultSet countQuotes() {
        ResultSet resultSet = null;

        String select = "SELECT COUNT(*) AS rowcount FROM teacher_quotes WHERE id_user=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, currentUser.getId());
            resultSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultSet;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
