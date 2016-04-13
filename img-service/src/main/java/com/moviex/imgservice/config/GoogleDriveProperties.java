package com.moviex.imgservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "drive")
public class GoogleDriveProperties {

    @Valid
    @NotNull
    private String imagesFolderId;

    @Valid
    @NotNull
    private String cdnUrl;
}
