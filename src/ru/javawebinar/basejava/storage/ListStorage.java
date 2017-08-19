package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] r = new Resume[size()];
        storageList.toArray(r);
        return r;
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected void saveElement(Resume r) {
        storageList.add(r);
    }

    @Override
    protected void insertElement(Resume r, Object index) {
        storageList.set((Integer) index, r);
    }

    @Override
    protected Resume getElement(String uuid) {
        return storageList.get((Integer) getIndex(uuid));
    }

    @Override
    protected void deleteElement(String uuid) {
        int index = (Integer) getIndex(uuid);
        storageList.remove(index);
    }

    @Override
    protected Object getIndex(String uuid) {
        int index = -1;
        for (Resume r : storageList) {
            if (uuid.equals(r.getUuid()))
                index = storageList.indexOf(r);
        }
        return index;
    }

    @Override
    protected boolean isContains(Object index) {
        return (Integer) index >= 0;
    }
}