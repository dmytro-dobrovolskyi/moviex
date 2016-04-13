package com.moviex.imgservice.service.impl;

import com.moviex.imgservice.entity.Image;
import com.moviex.imgservice.repository.ImageRepository;
import com.moviex.imgservice.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImageRepository imageRepository;

    @Async
    @Override
    @Transactional
    public void saveImages(Collection<Image> imageIds) {
        imageRepository.save(imageIds);
    }
}
