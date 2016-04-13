package com.moviex.imgservice.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.moviex.imgservice.exception.GoogleDriveAuthException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({GoogleDriveProperties.class})
public class ApplicationConfig {

    @Autowired
    private GoogleDriveProperties googleDriveProperties;

    @Bean
    public Drive googleDriveService() {
        NetHttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(new JacksonFactory())
                .setClientSecrets(
                        "608716674531-gn29p5thvf2gugjli96ivf50rfltfmm3.apps.googleusercontent.com",
                        "0pet1kLOnflQp7JJSjm7fimY"
                )
                .setTokenServerUrl(new GenericUrl("https://www.googleapis.com/oauth2/v3/token"))
                .build();
        credential.setRefreshToken("1/ieUruKngc7H6BaggogoMlkIzugTHWqKT5IhAXAXJ5hAMEudVrK5jSpoR30zcRFq6");
        refreshToken(credential);

        return new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("moviex").build();
    }

    @SneakyThrows(IOException.class)
    private void refreshToken(GoogleCredential credential) {
        if (!credential.refreshToken()) {
            throw new GoogleDriveAuthException("Could not refresh token. It may have expired");
        }
    }
}
