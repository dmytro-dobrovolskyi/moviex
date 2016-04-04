package com.moviex.business.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.exception.MovieRequestFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ObjectMapperUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapFromString(String dataToMap, Class<? extends T> mappedObjClass) {
        try {
            return objectMapper.readValue(dataToMap, mappedObjClass);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            throw new MovieRequestFailedException(dataToMap);
        }
    }
}
