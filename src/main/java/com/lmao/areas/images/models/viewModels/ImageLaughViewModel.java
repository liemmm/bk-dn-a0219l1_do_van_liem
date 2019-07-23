package com.lmao.areas.images.models.viewModels;

import com.lmao.areas.comments.models.viewModels.CommentViewModel;
import com.lmao.areas.users.models.viewModels.UserViewModelForLaughCard;

import java.util.Date;
import java.util.Set;

public class ImageLaughViewModel {

    private String id;

    private String imageUrl;

    private String description;

    private Date uploadedDate;

    private UserViewModelForLaughCard user;

    private Set<CommentViewModel> comments;

    public ImageLaughViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public UserViewModelForLaughCard getUser() {
        return user;
    }

    public void setUser(UserViewModelForLaughCard user) {
        this.user = user;
    }

    public Set<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentViewModel> comments) {
        this.comments = comments;
    }
}
