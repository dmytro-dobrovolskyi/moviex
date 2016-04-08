package com.moviex.imgservice;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;

public class ImgDownloader {
    private static final String DOWNLOAD_FOLDER = "img";

    @SneakyThrows(IOException.class)
    public static void download() {
        try (InputStream in = new BufferedInputStream(new URL("http://ia.media-imdb.com/images/M/MV5BMjA5NjA5NzE4OF5BMl5BanBnXkFtZTgwNzUxNDg4NjE@._V1_SX300.jpg").openStream());
             OutputStream out = new FileOutputStream(DOWNLOAD_FOLDER + "1.jpg")) {
            IOUtils.copy(in, out);
        }
    }
}
