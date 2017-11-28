package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        AbstractStorage storage = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Select a storage type:" + "\n" +
                "1 - AbstractFileStorage" + "\n" +
                "2 - AbstractPathStorage");

        String storageType = null;
        try {
            storageType = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (storageType != null && storageType.equals("1")) {
            storage = new ObjectStreamStorage(new File("g:\\Java\\BaseJava\\out\\production\\WorkingDirectory\\"));
        } else if (storageType != null && storageType.equals("2")) {
            storage = new ObjectStreamPathStorage(Paths.get("g:\\Java\\BaseJava\\out\\production\\WorkingDirectory\\"));
        }
    }
}
