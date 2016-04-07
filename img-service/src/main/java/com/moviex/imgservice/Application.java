package com.moviex.imgservice;


import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Application {
    private static final String DOWNLOAD_FOLDER = "/img";

    public static void main(String[] args) throws Exception {
        try (InputStream in = new BufferedInputStream(new URL("http://ia.media-imdb.com/images/M/MV5BMjA5NjA5NzE4OF5BMl5BanBnXkFtZTgwNzUxNDg4NjE@._V1_SX300.jpg").openStream());
             OutputStream out = new FileOutputStream(DOWNLOAD_FOLDER + "1")) {
            IOUtils.copy(in, out);
        }
    }
}
