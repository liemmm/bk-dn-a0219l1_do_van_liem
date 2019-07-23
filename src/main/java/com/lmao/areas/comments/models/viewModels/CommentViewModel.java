package com.lmao.areas.comments.models.viewModels;

import com.lmao.areas.users.models.viewModels.UserViewModelForComment;

import java.util.Date;

public class CommentViewModel {

    private String text;

    private UserViewModelForComment user;

    private Date commentedDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserViewModelForComment getUser() {
        return user;
    }

    public void setUser(UserViewModelForComment user) {
        this.user = user;
    }

    public Date getCommentedDate() {
        return commentedDate;
    }

    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = commentedDate;
    }
}
