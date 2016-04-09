package com.moviex.imgservice.controller;

import com.moviex.imgservice.service.ImgPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImgController {

    @Autowired
    private ImgPublicationService publicationService;

    @RequestMapping("publish")
    String publishImg(@RequestParam(required = true) String url) {
        return publicationService.publishImg(url);
    }
}
