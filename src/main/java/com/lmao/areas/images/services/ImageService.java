package com.lmao.areas.images.services;

import com.lmao.areas.images.entities.Image;
import com.lmao.areas.images.models.bindingModels.UploadImageBindingModel;
import com.lmao.areas.images.models.bindingModels.UploadImageFileBindingModel;
import com.lmao.areas.images.models.viewModels.ImageLaughViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image getOneById(String id);

    void save(UploadImageBindingModel uploadImageBindingModel);

    void save(UploadImageFileBindingModel uploadImageFileBindingModel, MultipartFile file);

    List<ImageLaughViewModel> getInitialImagesForMainbar(String categoryId);

    List<ImageLaughViewModel> getDynamiclyLoadedImagesForMainbarBelowId(String id, String categoryId);

    ImageLaughViewModel getImageLaughViewModelById(String id);

    Image findOneById(String id);
}
