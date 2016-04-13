package com.moviex.imgservice.service.impl;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.moviex.imgservice.config.GoogleDriveProperties;
import com.moviex.imgservice.entity.Image;
import com.moviex.imgservice.repository.ImageRepository;
import com.moviex.imgservice.service.ImgDownloadService;
import com.moviex.imgservice.service.ImgPublicationService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.Collections;

@Service
@Primary
public class AsyncImgPublicationService implements ImgPublicationService {

    @Autowired
    private GoogleDriveProperties driveProperties;

    @Autowired
    private AsyncImgPublisher asyncImgPublisher;

    private AsyncImgPublicationService() {

    }

    @Override
    public String publishImg(String imgUrl) {
        String imgId = constructImgId(imgUrl);

        asyncImgPublisher.publishImg(imgUrl, imgId);

        return String.format("%s%s/%s",
                driveProperties.getCdnUrl(),
                driveProperties.getImagesFolderId(),
                imgId
        );
    }

    public static String constructImgId(String imgUrl) {
        String imgName = StringUtils.substringBefore(StringUtils.substringAfterLast(imgUrl, "/"), "?");
        String ext = org.springframework.util.StringUtils.getFilenameExtension(imgName);
        imgName = StringUtils.remove(imgName, ext);
        imgName = StringUtils.remove(imgName, '.');
        int urlHashCode = Math.abs(imgUrl.hashCode());

        return imgName + "-" + urlHashCode + (ext == null ? "" : ext);
    }
}

@Service
class AsyncImgPublisher {

    @Autowired
    private Drive driveService;

    @Autowired
    private GoogleDriveProperties driveProperties;

    @Autowired
    private ImgDownloadService imgDownloadService;

    @Autowired
    private ImageRepository imageRepository;

    AsyncImgPublisher() {

    }

    @Async
    @SneakyThrows
    public void publishImg(String imgUrl, String imgId) {
        try (InputStream imgStream = imgDownloadService.downloadAsInputStream(imgUrl)) {
            Image image = imageRepository.findOne(imgId);

            if (image == null) {
                driveService.files()
                        .create(new File()
                                        .setParents(Collections.singletonList(driveProperties.getImagesFolderId()))
                                        .setName(imgId),
                                new InputStreamContent("*/*", imgStream))
                        .execute();
                image = new Image(imgId);
            }
            image.setLastTimeRequested(LocalTime.now());
            imageRepository.save(image);
        }
    }
}
