package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    private static String tabs = "";

    public static void main(String[] args) {
        getFiles(new File("."));
    }

    private static void getFiles(File file) {
        Objects.requireNonNull(file, "file must not be null");
        assert file.listFiles() != null;
        if (file.isDirectory()) {
            System.out.println(tabs + "\t" + file.getName().toUpperCase() + "\\");
            File[] fileList = file.listFiles();
            if (fileList != null) {
                tabs += "\t";
                for (File dir : fileList) {
                    getFiles(dir);
                }
            }
        }
        if (file.isFile()) System.out.println(tabs + file.getName());
    }

}