package com.moviex.imgservice;


import com.google.api.services.drive.Drive;

public class Application {
    public static void main(String[] args) throws Exception {
        Drive myDrive = GoogleDriveServiceFactory.createFor("dobrovolskyi.dmytro");

        new FilePrinter(myDrive).printAllFiles();
    }
}
