package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

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

    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        list.sort(RESUME_COMPARATOR);
        return list;
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

    protected abstract List<Resume> getList();

    static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    @Override
    public int compare(Resume o1, Resume o2) {
        int result = o1.getFullName().compareTo(o2.getFullName());
        return result != 0 ? result : o1.getUuid().compareTo(o2.getUuid());
    }
}