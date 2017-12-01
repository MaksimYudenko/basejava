package ru.javawebinar.basejava.storage;

public interface StorageStrategy {

    AbstractStorage createStorage(String str);

}