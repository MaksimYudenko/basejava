package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String uuid);

    protected abstract boolean isContains(Object searchKey);

    public void update(Resume r) {
        String uuid = r.getUuid();
        Object key = getKey(uuid);
        if (isContains(key)) {
            updateElement(r, key);
        } else throw new NotExistStorageException(uuid);
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object key = getKey(uuid);
        if (isContains(key)) {
            throw new ExistStorageException(uuid);
        } else saveElement(r, key);
    }

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (isContains(key)) {
            return getElement(key);
        } else throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (isContains(key)) {
            deleteElement(key);
        } else throw new NotExistStorageException(uuid);
    }

    protected abstract void updateElement(Resume r, Object searchKey);

    protected abstract void saveElement(Resume r, Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void deleteElement(Object searchKey);
}