package com.lmao.areas.comments.controllers;

import com.lmao.areas.comments.models.bindingModels.CommentBindingModel;
import com.lmao.areas.comments.models.viewModels.CommentViewModel;
import com.lmao.areas.comments.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentViewModel> saveComment(@RequestBody CommentBindingModel commentBindingModel){
        CommentViewModel commentViewModel = this.commentService.save(commentBindingModel);
        return new ResponseEntity(commentViewModel, HttpStatus.OK);
    }
}
