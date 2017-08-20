package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    private Object key;
    private String uuid;

    protected abstract Object getKey(String uuid);

    protected abstract boolean isContains(Object searchKey);

    private boolean isResumeExist(String id) {
        uuid = id;
        key = getKey(uuid);
        return isContains(key);
    }

    public void update(Resume r) {
        if (isResumeExist(r.getUuid())) {
            updateElement(r, key);
        } else throw new NotExistStorageException(uuid);
    }

    public void save(Resume r) {
        if (isResumeExist(r.getUuid())) {
            throw new ExistStorageException(uuid);
        } else saveElement(r, key);
    }

    public Resume get(String uuid) {
        if (isResumeExist(uuid)) {
            return getElement(key);
        } else throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        if (isResumeExist(uuid)) {
            deleteElement(key);
        } else throw new NotExistStorageException(uuid);
    }

    protected abstract void updateElement(Resume r, Object searchKey);

    protected abstract void saveElement(Resume r, Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void deleteElement(Object searchKey);
}