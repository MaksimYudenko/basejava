package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return uuid != null ? storageMap.get(uuid) : null;
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return searchKey != null && storageMap.containsKey(searchKey.toString());
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storageMap.get(searchKey.toString());
    }

    @Override
    protected void deleteElement(Object searchKey) {
        storageMap.remove(searchKey.toString());
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(storageMap.values());
        resumeList.sort(RESUME_COMPARATOR);
        return resumeList;
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}