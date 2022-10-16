package com.example.mall.entity;

import java.util.Date;

public class Comment {

    private int commentId;
    private Date commentTime;
    private int commentScore;
    private String commentContent;

    public Comment(int commentId, Date commentTime, int commentScore, String commentContent) {
        this.commentId = commentId;
        this.commentTime = commentTime;
        this.commentScore = commentScore;
        this.commentContent = commentContent;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentTime=" + commentTime +
                ", commentScore=" + commentScore +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }

}
