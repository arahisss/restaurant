package com.example.courseproject;

public class TeacherQuote {
    private String quote;
    private String teacher;
    private String subject;
    private String date;
    private long id;

    public TeacherQuote(long id, String teacher, String subject, String quote, String date) {
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.date = date;
        this.id = id;
    }

    public TeacherQuote(String teacher, String subject, String quote) {
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
