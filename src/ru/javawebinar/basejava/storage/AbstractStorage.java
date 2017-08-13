package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        String uuid = r.getUuid();
        if (isContains(uuid)) {
            insertElement(r, getIndex(uuid));
        } else throw new NotExistStorageException(uuid);
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (isContains(uuid)) {
            throw new ExistStorageException(uuid);
        } else saveElement(r);
    }

    public Resume get(String uuid) {
        if (isContains(uuid)) {
            return getElement(uuid);
        } else throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        if (isContains(uuid)) {
            deleteElement(uuid);
        } else throw new NotExistStorageException(uuid);
    }


    protected abstract void saveElement(Resume r);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Resume getElement(String uuid);

    protected abstract void deleteElement(String uuid);

    protected abstract int getIndex(String uuid);

    protected boolean isContains(String uuid) {
        return getIndex(uuid) >= 0;
    }
}