package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return directory.getNameCount();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(String.valueOf(directory.getFileName()), uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return path.isAbsolute();
    }

    @Override
    protected void doSave(Resume r, Path path) {
        Path newPath = Paths.get(String.valueOf(path));
        doUpdate(r, newPath);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        if (!path.isAbsolute()) {
            throw new StorageException("Path delete error", path.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        int pathsCount = directory.getNameCount();
        if (pathsCount <= 0) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(pathsCount);
        for (int i = 0; i < pathsCount; i++) {
            list.add(doGet(directory.getName(i)));
        }
        return list;
    }

}