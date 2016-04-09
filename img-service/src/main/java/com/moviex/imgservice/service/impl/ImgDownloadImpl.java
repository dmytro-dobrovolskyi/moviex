package com.moviex.imgservice.service.impl;

import com.moviex.imgservice.service.ImgDownloadService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImgDownloadImpl implements ImgDownloadService {

    @Override
    @SneakyThrows(IOException.class)
    public InputStream downloadAsInputStream(String url) {
        return new BufferedInputStream(new URL(url).openStream());
    }
}
