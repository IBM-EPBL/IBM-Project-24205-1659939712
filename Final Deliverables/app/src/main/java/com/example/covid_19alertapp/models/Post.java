package com.example.covid_19alertapp.models;

import java.util.List;

public class Post {
    private String postID;
    private String userName;
    private String userID;
    private String postBody;
    private String date;
    private String time;
    private String contactNO;
    private String postType;
    private List<Comment> Comments;
    private long commentCount=0;


    public Post() {
    }

    public Post(String postID, String userName, String userID, String postBody, String date, String time, String contactNO, String postType) {
        this.postID = postID;
        this.userName = userName;
        this.userID = userID;
        this.postBody = postBody;
        this.date = date;
        this.time = time;
        this.contactNO = contactNO;
        this.postType = postType;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContactNO() {
        return contactNO;
    }

    public void setContactNO(String contactNO) {
        this.contactNO = contactNO;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public List<Comment> getCommentList() {
        return Comments;
    }

    public void setCommentList(List<Comment> commentList) {
        this.Comments = commentList;
    }
}
