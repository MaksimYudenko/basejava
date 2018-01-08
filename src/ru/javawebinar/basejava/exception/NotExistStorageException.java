package ru.javawebinar.basejava.exception;

import java.sql.SQLException;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        this(uuid, null);
    }

    public NotExistStorageException(String uuid, SQLException e) {
        super("Resume " + uuid + " not exist", e);
    }
}
