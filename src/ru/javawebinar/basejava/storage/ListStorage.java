package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    protected Object getKey(String uuid) {
        int index = -1;
        for (Resume r : storageList) {
            if (uuid.equals(r.getUuid()))
                index = storageList.indexOf(r);
        }
        return index;
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    protected void updateElement(Resume r, Object searchKey) {
        storageList.set((Integer) searchKey, r);
    }

    @Override
    protected void saveElement(Resume r, Object searchKey) {
        storageList.add(r);
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return storageList.get((Integer) searchKey);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        int index = (Integer) searchKey;
        storageList.remove(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        Collections.sort(storageList, RESUME_COMPARATOR);
        return storageList;
    }

    @Override
    public int size() {
        return storageList.size();
    }
}