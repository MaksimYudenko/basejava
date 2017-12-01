package ru.javawebinar.basejava.storage;

import java.io.File;

public class FileStorage implements StorageStrategy {

    @Override
    public AbstractStorage createStorage(String file) {
        return new ObjectStreamStorage(new File(file));
    }

}