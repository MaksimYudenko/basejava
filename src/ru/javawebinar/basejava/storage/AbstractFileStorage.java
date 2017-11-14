package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
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

    @Override
    public void clear() {
        //directory = new File(directory.getAbsolutePath());
        for (File f : directory.listFiles()) {
            f.delete();
        }
    }

    @Override
    public int size() {
        return directory.listFiles().length;
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
            file.createNewFile();
            doWrite(r, file);
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
        for (File f : directory.listFiles()) {
            if (f.equals(file)) f.delete();
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = null;
        for (File f : directory.listFiles()) {
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
