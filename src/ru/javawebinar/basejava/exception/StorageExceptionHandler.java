package ru.javawebinar.basejava.exception;

import java.sql.SQLException;

public class StorageExceptionHandler extends StorageException {

    public StorageExceptionHandler(SQLException e) {
        super(e);
        if (e.getSQLState().equalsIgnoreCase("23505")) {
            throw new ExistStorageException(e, e.getMessage());
        } else
            throw new StorageException(e.getMessage());
    }
}