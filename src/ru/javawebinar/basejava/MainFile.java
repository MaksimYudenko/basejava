package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        getFiles(new File("."));
    }

    private static void getFiles(File file) {
        if (file.isDirectory()) {
            try {System.out.println(file.getCanonicalPath());}
            catch (IOException e) {e.printStackTrace();}
            for (File dir : file.listFiles()) getFiles(dir);
        } else {System.out.println("\t" + file.getName());}
    }

}