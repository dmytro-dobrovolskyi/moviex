package com.moviex.imgservice.service;

import java.io.InputStream;

public interface ImgDownloadService {
    InputStream downloadAsInputStream(String url);
}
