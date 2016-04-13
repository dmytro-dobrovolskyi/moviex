package com.moviex.imgservice.repository;

import com.moviex.imgservice.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, String> {

}
