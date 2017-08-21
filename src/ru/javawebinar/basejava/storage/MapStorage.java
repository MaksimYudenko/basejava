package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
       /* String index = null;
        for (String key : storageMap.keySet()) {
            if (uuid.equals(key)) {
                index = key;
            }
        }
        return index;*/
        return uuid;
    }

    @Override
    protected boolean isContains(Object searchKey) {
        //   return searchKey != null;
        return false;
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storageMap.put((String) searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storageMap.get(searchKey);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        storageMap.remove(searchKey);
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
}