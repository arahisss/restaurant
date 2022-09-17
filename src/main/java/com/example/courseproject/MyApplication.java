package com.example.courseproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 527);
        stage.setTitle("Цитаты преподавателей");
        stage.setScene(scene);
        stage.show();

//        stage.getScene().setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.UP){
//                System.out.println("2");
//            }
//        });
    }

//    public static byte[] hashingPassword(String passwordToHash) throws NoSuchAlgorithmException {
////        SecureRandom random = new SecureRandom();
////        byte[] salt = new byte[16];
////        random.nextBytes(salt);
//
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
////        md.update(salt);
//
//        byte[] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
//
//        return hashedPassword;
//
//    }
//
//    public static byte[] generateSalt() throws NoSuchAlgorithmException {
//        // VERY important to use SecureRandom instead of just Random
////        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        SecureRandom random = new SecureRandom();
//
//        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//
//        return salt;
//    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        launch();


//        byte[] salt = generateSalt();

//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[8];
//        random.nextBytes(salt);
//
//
//        byte[] h1 = hashingPassword("hello1234");
//        byte[] h2 = hashingPassword("hello1234");
//
//        System.out.println(h1);
//        System.out.println(h2);
//
//
//        StringBuilder builder1 = new StringBuilder();
//        StringBuilder builder2 = new StringBuilder();
//
//
//        for (byte b : h1) {
//            builder1.append(String.format("%02X", b));
//        }
//
//        for (byte b : h2) {
//            builder2.append(String.format("%02X", b));
//        }
//
//        System.out.println(builder1);
//        System.out.println(builder2);
//        System.out.println(h1.equals(h2));


//[B@5bcab519,     [B@5f2108b5

    }
}
//        String str = "s64-PL0__253TO";
//        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//
//        byte[] bytes = md5.digest(str.getBytes());
//        StringBuilder builder = new StringBuilder();
//        System.out.println(bytes.toString());
//
//        for (byte b : bytes) {
//            builder.append(String.format("%02X", b));
//        }
//        System.out.println(builder.toString());

//        String s = "75 73 A4 FB D6 60 B4 0D AE E5 13 1F 44 0D 9F F3";
//        char[] s1 = s.toCharArray();
//        for (char c : s1) {
//            if (c == ' ') {
//            }
//        }
















