package com.moviex.imgservice.controller;

import com.moviex.imgservice.service.ImgPublicationService;
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

    private ImgController() {

    }

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    Collection<String> publishImg(@RequestBody List<String> urls) {
        return urls.stream()
                .map(publicationService::publishImg)
                .collect(Collectors.toList());
    }
}
