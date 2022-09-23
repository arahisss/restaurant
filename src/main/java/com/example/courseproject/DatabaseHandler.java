package com.example.courseproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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


    // Добавление записи в таблицу users
    public void signUpUser(User user) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String insert = "INSERT INTO users (login, hash_password) VALUES(?, ?)";
        //Todo: параметрический запрос

        currentUser = user;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            //Todo: сделать хэш пароля
            prSt.setString(2, user.getPassword());

            prSt.executeUpdate();

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


    // Получение записи из таблицы users
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

                TeacherQuote quote = new TeacherQuote(resultSet.getString("teacher"),
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
}
