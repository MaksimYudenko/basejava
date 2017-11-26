package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    /* public static void main(String[] args) {
         String filePath = ".\\.gitignore";

         File file = new File(filePath);
         try {
             System.out.println(file.getCanonicalPath());
         } catch (IOException e) {
             throw new RuntimeException("Error", e);
         }

         File dir = new File("./src/ru/javawebinar/basejava");
         System.out.println(dir.isDirectory());
         String[] list = dir.list();
         if (list != null) {
             for (String name : list) {
                 System.out.println(name);
             }
         }

         try (FileInputStream fis = new FileInputStream(filePath)) {
             System.out.println(fis.read());
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         printDirectoryDeeply(dir);
     }

     // TODO: make pretty output
     public static void printDirectoryDeeply(File dir) {
         File[] files = dir.listFiles();

         if (files != null) {
             for (File file : files) {
                 if (file.isFile()) {
                     System.out.println("File: " + file.getName());
                 } else if (file.isDirectory()) {
                     System.out.println("Directory: " + file.getName());
                     printDirectoryDeeply(file);
                 }
             }
         }
     */
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