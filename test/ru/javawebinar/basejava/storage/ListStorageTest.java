package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public class ListStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    private ListStorage storageList = new ListStorage();

    @Before
    public void setUp() throws Exception {
        storageList.clear();
        storageList.save(RESUME_1);
        storageList.save(RESUME_2);
        storageList.save(RESUME_3);
        storageList.save(RESUME_4);
    }

    @Test
    public void size() {
        assertSize(4);
    }

    @Test
    public void clear() throws Exception {
        storageList.clear();
    }

    @Test
    public void update() throws Exception {
        Resume r = new Resume(UUID_2);
        storageList.update(r);
        assertTrue(r.equals(storageList.get(UUID_2)));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storageList.getAll();
        assertEquals(4, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_4, array[3]);
    }

    @Test
    public void save() throws Exception {
        Resume r = new Resume("uuid5");
        storageList.save(r);
        assertSize(5);
        assertGet(r);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storageList.delete(UUID_1);
        assertSize(3);
        storageList.get(UUID_1);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
        assertGet(RESUME_4);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storageList.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storageList.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storageList.size());
    }
}