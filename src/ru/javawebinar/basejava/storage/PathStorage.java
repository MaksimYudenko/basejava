package ru.javawebinar.basejava.storage;

public class PathStorage implements StorageStrategy {

    @Override
    public AbstractStorage createStorage(String path) {
        return new ObjectStreamPathStorage(path);
    }

}