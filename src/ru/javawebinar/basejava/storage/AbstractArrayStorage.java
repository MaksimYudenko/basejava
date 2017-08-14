package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    protected void saveElement(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected abstract void insertElement(Resume r, int index);

    @Override
    protected Resume getElement(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    protected void deleteElement(int index) {
        fillDeletedElement(index);
        size--;
    }

    protected abstract void fillDeletedElement(int index);
}