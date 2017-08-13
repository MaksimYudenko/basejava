package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new TreeMap<>();

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
    protected void insertElement(Resume r, int index) {
        for (Map.Entry<String, Resume> entry : storageMap.entrySet()) {
            if (r.getUuid().equals(entry.getKey())) {
                entry.setValue(r);
            }
        }
    }

    @Override
    protected Resume getElement(String uuid) {
        Resume r = null;
        for (Map.Entry<String, Resume> entry : storageMap.entrySet()) {
            if (uuid.equals(entry.getKey())) r = entry.getValue();
        }

        return r;
    }

    @Override
    protected void deleteElement(String uuid) {
        storageMap.remove(uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (Map.Entry<String, Resume> entry : storageMap.entrySet()) {
            if (uuid.equals(entry.getKey())) {
                String key = entry.getKey();
                char[] keyChars = key.toCharArray();
                index = Character.getNumericValue(keyChars[keyChars.length - 1]);
            }
        }
        return index;
    }
}