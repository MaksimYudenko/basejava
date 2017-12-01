package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.FileStorage;
import ru.javawebinar.basejava.storage.PathStorage;
import ru.javawebinar.basejava.storage.StorageCreator;

public class MainStrategy {

    public static void main(String[] args) {

        StorageCreator creator;
        creator = new StorageCreator(new FileStorage());
        creator.create(".");
        creator = new StorageCreator(new PathStorage());
        creator.create(".");
    }

}