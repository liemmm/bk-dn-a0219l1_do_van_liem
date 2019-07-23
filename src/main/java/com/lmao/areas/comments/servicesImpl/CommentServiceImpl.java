package com.lmao.areas.comments.servicesImpl;

import com.lmao.areas.comments.entities.Comment;
import com.lmao.areas.comments.models.bindingModels.CommentBindingModel;
import com.lmao.areas.comments.models.viewModels.CommentViewModel;
import com.lmao.areas.comments.repositories.CommentRepository;
import com.lmao.areas.comments.services.CommentService;
import com.lmao.areas.images.entities.Image;
import com.lmao.areas.images.services.ImageService;
import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentViewModel save(CommentBindingModel commentBindingModel) {
        Comment comment = new Comment();
        comment.setText(commentBindingModel.getText());

        String laughId =  commentBindingModel.getIdOfImage();
        Image image = this.imageService.getOneById(laughId);
        comment.setImage(image);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        user = this.userService.findOneByUsername(username);
        comment.setUser(user);

        comment.setCommentedDate(new Date());

        this.commentRepository.save(comment);

        CommentViewModel commentViewModel = this.modelMapper.map(comment, CommentViewModel.class);
        return commentViewModel;
    }
}
