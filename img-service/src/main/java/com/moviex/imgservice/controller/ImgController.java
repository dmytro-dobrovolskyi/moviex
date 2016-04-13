package com.moviex.imgservice.controller;

import com.moviex.imgservice.config.GoogleDriveProperties;
import com.moviex.imgservice.entity.Image;
import com.moviex.imgservice.service.ImgPublicationService;
import com.moviex.imgservice.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImgController {

    @Autowired
    private ImgPublicationService publicationService;

    @Autowired
    private GoogleDriveProperties driveProperties;

    @Autowired
    private ImgService imgService;

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    Collection<String> publishImg(@RequestBody List<String> urls) {
        List<Image> publishedImages = urls.parallelStream()
                .map(publicationService::publishImg)
                .collect(Collectors.toList());

        imgService.saveImages(publishedImages);

        String cdnUrl = driveProperties.getCdnUrl();
        String imagesFolderId = driveProperties.getImagesFolderId();

        return publishedImages.stream()
                .map(image -> String.format("%s%s/%s",
                        cdnUrl, imagesFolderId, image.getId()))
                .collect(Collectors.toList());
    }
}
