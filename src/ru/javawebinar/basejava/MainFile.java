package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        getFiles(new File("."));
    }

    private static void getFiles(File file) {
        Objects.requireNonNull(file, "file must not be null");
        assert file.listFiles() != null;
        if (file.isDirectory()) {
            System.out.println(file.getName());
            File[] fileList = file.listFiles();
            if (fileList != null) for (File dir : fileList) getFiles(dir);
        }
        if (file.isFile()) System.out.println("\t" + file.getName());
    }

}