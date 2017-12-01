package ru.javawebinar.basejava.storage;

public class StorageCreator {

    private StorageStrategy strategy;

    public StorageCreator(StorageStrategy strategy) {
        this.strategy = strategy;
    }

    public void create(String str) {
        strategy.createStorage(str);
    }

}