package ru.javawebinar.basejava.exception;

import java.sql.SQLException;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        this(uuid, null);
    }

    public ExistStorageException(String uuid, SQLException e) {
        super("Resume " + uuid + " already exist", e);
    }
}