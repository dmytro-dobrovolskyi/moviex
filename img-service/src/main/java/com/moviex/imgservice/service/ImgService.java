package com.moviex.imgservice.service;

import com.moviex.imgservice.entity.Image;

import java.util.Collection;

public interface ImgService {
    void saveImages(Collection<Image> imageIds);
}
