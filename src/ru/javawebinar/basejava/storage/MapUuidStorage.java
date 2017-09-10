package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return storageMap.containsKey(searchKey.toString());
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
        storageMap.put((String) searchKey, r);
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
    protected List<Resume> getList() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}