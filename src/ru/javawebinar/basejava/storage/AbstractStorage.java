package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (isContains(index)) {
            insertElement(r, index);
        } else throw new NotExistStorageException(uuid);
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (isContains(index)) {
            throw new ExistStorageException(uuid);
        } else saveElement(r);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isContains(index)) {
            return getElement(uuid);
        } else throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isContains(index)) {
            deleteElement(index);
        } else throw new NotExistStorageException(uuid);
    }


    protected abstract void saveElement(Resume r);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Resume getElement(String uuid);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);

    protected boolean isContains(int index) {
        return index >= 0;
    }
}