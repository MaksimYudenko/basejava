package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        storageMap.values().toArray(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    protected void saveElement(Resume r) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected void insertElement(Resume r, Object index) {
//        storageMap.put(r.getUuid(), r);
        storageMap.put((String) index, r);
    }

    @Override
    protected Resume getElement(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected void deleteElement(String uuid) {
        storageMap.remove(uuid);
    }

    @Override
    protected String getIndex(String uuid) {
        String index = null;
        for (String key : storageMap.keySet()) {
            if (uuid.equals(key)) {
                index = key;
            }
        }
        return index;
    }

    @Override
    protected boolean isContains(Object index) {
        return index != null;
    }
}