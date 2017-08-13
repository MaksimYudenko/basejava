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
    protected void insertElement(Resume r, int index) {
        for (Resume resume : storageList) {
            if (storageList.get(index).equals(resume))
                storageList.set(index, r);
        }
    }

    @Override
    protected Resume getElement(String uuid) {
        return storageList.get(getIndex(uuid));
    }

    @Override
    protected void deleteElement(String uuid) {
        storageList.remove(getIndex(uuid));
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (Resume r : storageList) {
            if (uuid.equals(r.getUuid()))
                index = storageList.indexOf(r);
        }
        return index;
    }
}