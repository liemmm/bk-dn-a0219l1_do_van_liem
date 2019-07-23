package com.lmao.areas.images.servicesImpl;

import com.lmao.areas.categories.entities.Category;
import com.lmao.areas.categories.services.CategoryService;
import com.lmao.areas.images.entities.Image;
import com.lmao.areas.images.models.bindingModels.UploadImageBindingModel;
import com.lmao.areas.images.models.bindingModels.UploadImageFileBindingModel;
import com.lmao.areas.images.models.viewModels.ImageLaughViewModel;
import com.lmao.areas.images.respositories.ImageRepository;
import com.lmao.areas.images.services.ImageService;
import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.services.UserService;
import com.lmao.cloud.CloudImageUploader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    private final CloudImageUploader cloudImageUploader;

    public ImageServiceImpl(CloudImageUploader cloudImageUploader) {
        this.cloudImageUploader = cloudImageUploader;
    }

    @Override
    public Image getOneById(String id) {
        return this.imageRepository.findOne(id);
    }

    @Override
    public void save(UploadImageBindingModel uploadImageBindingModel) {
        Image image = this.modelMapper.map(uploadImageBindingModel, Image.class);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        user = this.userService.findOneByUsername(username);

        image.setUser(user);
        image.setUploadedDate(new Date());

        this.imageRepository.save(image);
    }

    @Override
    public void save(UploadImageFileBindingModel uploadImageFileBindingModel, MultipartFile file) {
        String imageUrl = null;
        try {
            imageUrl = this.cloudImageUploader.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = this.modelMapper.map(uploadImageFileBindingModel, Image.class);

        Category category = this.categoryService.findById(uploadImageFileBindingModel.getCategoryId());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = null;
        user = this.userService.findOneByUsername(username);

        image.setImageUrl(imageUrl);
        image.setUser(user);
        image.setCategory(category);
        image.setUploadedDate(new Date());

        this.imageRepository.save(image);
    }

    @Override
    public List<ImageLaughViewModel> getInitialImagesForMainbar(String categoryId) {
        List<ImageLaughViewModel> imageLaughViewModels = new ArrayList<>();
        List<Image> images = null;
        if (!categoryId.equals("all")) {
            images = this.imageRepository.getInitialImagesForHomeWithCategory(categoryId);
        }

        if (images == null) {
            images = this.imageRepository.getInitialImagesForHome();
        }

        for (Image image : images) {
            ImageLaughViewModel imageLaughViewModel = this.modelMapper.map(image, ImageLaughViewModel.class);
            imageLaughViewModels.add(imageLaughViewModel);
        }

        return imageLaughViewModels;
    }

    @Override
    public List<ImageLaughViewModel> getDynamiclyLoadedImagesForMainbarBelowId(String loadedImagesCount, String categoryId) {
        List<ImageLaughViewModel> imageLaughViewModels = new ArrayList<>();
        List<Image> images = null;
        if (!categoryId.equals("all")) {
            images = this.imageRepository.getImagesForHomePageWithOffsetAndCategory(Integer.parseInt(loadedImagesCount), 3, categoryId);
        }

        if (images == null) {
            images = this.imageRepository.getImagesForHomePageWithOffset(Integer.parseInt(loadedImagesCount), 3);
        }

        for (Image image : images) {
            ImageLaughViewModel imageLaughViewModel = this.modelMapper.map(image, ImageLaughViewModel.class);
            imageLaughViewModels.add(imageLaughViewModel);
        }

        return imageLaughViewModels;
    }

    @Override
    public ImageLaughViewModel getImageLaughViewModelById(String id) {
        Image imageLaugh = this.imageRepository.findOne(id);
        ImageLaughViewModel imageLaughViewModel = this.modelMapper.map(imageLaugh, ImageLaughViewModel.class);
        return imageLaughViewModel;
    }

    @Override
    public Image findOneById(String id) {
        Image image = this.imageRepository.findOne(id);
        return image;
    }
}
