package com.moviex.imgservice;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.moviex.imgservice.exception.NoTokenProvidedException;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements ApplicationRunner {
    private static final String APPLICATION_NAME = "img-service";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private String accessToken;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @SneakyThrows(IOException.class)
    public void run(ApplicationArguments args) {
        String accessToken = args.getOptionValues("token").stream()
                .findFirst()
                .orElseThrow(NoTokenProvidedException::new);

        new FilePrinter(new Drive.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GoogleCredential().setAccessToken(accessToken)
        )
        .setApplicationName(APPLICATION_NAME)
        .build()).printAllFiles();
    }
}
