package com.lmao.areas.comments.services;


import com.lmao.areas.comments.models.bindingModels.CommentBindingModel;
import com.lmao.areas.comments.models.viewModels.CommentViewModel;

public interface CommentService {

    CommentViewModel save(CommentBindingModel commentBindingModel);
}
