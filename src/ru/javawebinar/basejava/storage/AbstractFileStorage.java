package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() +
                    " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() +
                    " is not readable/writable");
        }
        this.directory = directory;
    }

    private File[] getFilesList(File directory) {
        File[] fl = directory.listFiles();
        if (fl == null) {
            throw new StorageException("Directory read error", null);
        } else {
            return fl;
        }
    }

    @Override
    public void clear() {
        //directory = new File(directory.getAbsolutePath());
        for (File f : getFilesList(directory)) {
            if (!f.delete()) System.out.println("Directory is not cleared.");
        }
    }

    @Override
    public int size() {
        return getFilesList(directory).length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error (updating)", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (!file.createNewFile()) {
                System.out.println("File is not saved.");
            } else {
                doWrite(r, file);
            }
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        Resume resume;
        try {
            resume = doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error (reading)", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) System.out.println("File is not deleted.");
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>();
        for (File f : getFilesList(directory)) {
            try {
                list.add(doRead(f));
            } catch (IOException e) {
                throw new StorageException("IO error (copyingAll)", f.getName(), e);
            }
        }
        return list;
    }

    abstract void doWrite(Resume r, File file) throws IOException;

    abstract Resume doRead(File file) throws IOException;

}