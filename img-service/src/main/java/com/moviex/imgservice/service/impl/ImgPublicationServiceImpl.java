package com.moviex.imgservice.service.impl;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.moviex.imgservice.service.ImgDownloadService;
import com.moviex.imgservice.service.ImgPublicationService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Service
public class ImgPublicationServiceImpl implements ImgPublicationService {

    @Autowired
    private Drive driveService;

    @Autowired
    private ImgDownloadService imgDownloadService;

    @Override
    @SneakyThrows(IOException.class)
    public String publishImg(String imgUrl) {
        try (InputStream imgStream = imgDownloadService.downloadAsInputStream(imgUrl)) {
            String imgId = imgUrl.hashCode() + StringUtils.substringAfterLast(imgUrl, "/");

            if (!driveService.files().get(imgId).isEmpty()) {

                driveService.files()
                        .create(new File()
                                        .setParents(Collections.singletonList("0B5bvhq7seDzuM3dvR05ZUGFTcXc"))
                                        .setName(imgId)
                                        .setId(imgId),
                                new InputStreamContent("*/*", imgStream))
                        .execute();
            }
            return imgId;
        }
    }
}
