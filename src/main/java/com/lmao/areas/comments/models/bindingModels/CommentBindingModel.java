package com.lmao.areas.comments.models.bindingModels;

public class CommentBindingModel {

    private String text;

    private String idOfImage;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIdOfImage() {
        return idOfImage;
    }

    public void setIdOfImage(String idOfImage) {
        this.idOfImage = idOfImage;
    }
}
