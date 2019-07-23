package com.lmao.areas.images.controllers;

import com.lmao.areas.images.models.viewModels.ImageLaughViewModel;
import com.lmao.areas.images.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/image")
public class UnauthorizedLaughController {

    private final ImageService imageService;

    @Autowired
    public UnauthorizedLaughController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/get-initial-images-for-mainbar")
    public ResponseEntity<List<ImageLaughViewModel>> getInitialImageLaughsForMainbar(@RequestParam(required = false, name = "categoryId", defaultValue = "all") String categoryId) {
        List<ImageLaughViewModel> imageLaughViewModels = this.imageService.getInitialImagesForMainbar(categoryId);
        ResponseEntity<List<ImageLaughViewModel>> responseEntity =
                new ResponseEntity(imageLaughViewModels, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/get-dynamically-loaded-images-for-mainbar")
    public ResponseEntity<List<ImageLaughViewModel>> getDynamiclyLoadedImagesForMainbar(
            @RequestParam(required = true, name = "loadedImagesCount") String loadedImagesCount, @RequestParam(required = false, name = "categoryId", defaultValue = "all") String categoryId) {
        List<ImageLaughViewModel> imageLaughViewModels = this.imageService.getDynamiclyLoadedImagesForMainbarBelowId(loadedImagesCount, categoryId);
        ResponseEntity<List<ImageLaughViewModel>> responseEntity =
                new ResponseEntity(imageLaughViewModels, HttpStatus.OK);

        return responseEntity;
    }
}
