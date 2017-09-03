package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage, Comparator<Resume> {

    protected abstract Object getKey(String uuid);

    protected abstract boolean isContains(Object searchKey);

    public void update(Resume r) {
        Object key = containsKey(r.getUuid());
        updateElement(r, key);
    }

    public void save(Resume r) {
        Object key = notContainsKey(r.getUuid());
        saveElement(r, key);
    }

    public Resume get(String uuid) {
        Object key = containsKey(uuid);
        return getElement(key);
    }

    public void delete(String uuid) {
        Object key = containsKey(uuid);
        deleteElement(key);
    }

    private Object containsKey(String uuid) {
        Object key = getKey(uuid);
        if (!isContains(key)) throw new NotExistStorageException(uuid);
        return key;
    }

    private Object notContainsKey(String uuid) {
        Object key = getKey(uuid);
        if (isContains(key)) throw new ExistStorageException(uuid);
        return key;
    }

    protected abstract void updateElement(Resume r, Object searchKey);

    protected abstract void saveElement(Resume r, Object searchKey);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void deleteElement(Object searchKey);

    static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}