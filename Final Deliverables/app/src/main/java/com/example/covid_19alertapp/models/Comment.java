package com.example.covid_19alertapp.models;

public class Comment {
    private String comment_id;
    private String user_name;
    private String user_id;
    private String post_id;
    private String comment_text;
    private String comment_date;
    private String comment_time;

    public Comment() {
    }

    public Comment(String comment_id, String user_name, String user_id, String post_id, String comment_text, String comment_date, String comment_time) {
        this.comment_id = comment_id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_text = comment_text;
        this.comment_date = comment_date;
        this.comment_time = comment_time;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }
}
