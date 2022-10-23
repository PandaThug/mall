package com.example.mall.entity;

import java.util.Date;

public class Comment {

    private int commentId;
    private int commentScore;
    private String commentContent;
    private int commentStore;
    private int commentGood;

    public Comment(int commentId, int commentScore, String commentContent, int commentStore, int commentGood) {
        this.commentId = commentId;
        this.commentScore = commentScore;
        this.commentContent = commentContent;
        this.commentStore = commentStore;
        this.commentGood = commentGood;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public int getCommentStore() {
        return commentStore;
    }

    public void setCommentStore(int commentStore) {
        this.commentStore = commentStore;
    }

    public int getCommentGood() {
        return commentGood;
    }

    public void setCommentGood(int commentGood) {
        this.commentGood = commentGood;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentScore=" + commentScore +
                ", commentContent='" + commentContent + '\'' +
                ", commentStore=" + commentStore +
                ", commentGood=" + commentGood +
                '}';
    }

}
