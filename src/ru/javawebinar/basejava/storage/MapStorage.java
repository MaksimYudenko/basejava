package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storageMap.put(((Resume) searchKey).getUuid(), r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storageMap.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected void deleteElement(Object searchKey) {
        storageMap.remove(((Resume) searchKey).getUuid());
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