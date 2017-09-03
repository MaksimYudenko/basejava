package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<Integer, Resume> storageMap = new HashMap<>();

    @Override
    protected Integer getKey(String uuid) {
       /* int index = 0;
        for (Integer key : storageMap.keySet()) {
            if (uuid.hashCode() == key) {
                index = key;
            }
        }*/
        return uuid.hashCode();
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return (Integer) searchKey > 0;
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storageMap.put((Integer) searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        storageMap.put((Integer) searchKey, r);
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
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(storageMap.values());
        resumeList.sort(RESUME_COMPARATOR);
        return resumeList;
    }

    @Override
    public int size() {
        return storageMap.size();
    }


    /*

    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Integer getKey(String uuid) {
       */
/* String index = null;
        for (String key : storageMap.keySet()) {
            if (uuid.equals(key)) {
                index = key;
            }
        }
        return index;*//*

        return uuid.hashCode();
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return (Integer) searchKey > 0;
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
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>(storageMap.values());
        resumeList.sort(RESUME_COMPARATOR);
        return resumeList;
    }

    @Override
    public int size() {
        return storageMap.size();
    }
*/
}