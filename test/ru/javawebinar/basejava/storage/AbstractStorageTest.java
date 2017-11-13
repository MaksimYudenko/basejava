package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");
    }

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void setPhoneNumber() {
        RESUME_1.setContact(ContactType.PHONE, "1234567");
        RESUME_2.setContact(ContactType.PHONE, "2234567");
        RESUME_3.setContact(ContactType.PHONE, "3234567");
        RESUME_4.setContact(ContactType.PHONE, "4234567");
        assertEquals(RESUME_1.getContact(ContactType.PHONE), "1234567");
    }

    @Test
    public void setMail() {
        RESUME_1.setContact(ContactType.MAIL, "yudenkomaksim@gmail.com");
        RESUME_2.setContact(ContactType.MAIL, "abc@gmail.com");
        RESUME_3.setContact(ContactType.MAIL, "def@gmail.com");
        RESUME_4.setContact(ContactType.MAIL, "ghi@gmail.com");
        assertEquals(RESUME_1.getContact(ContactType.MAIL), "yudenkomaksim@gmail.com");
    }

    @Test
    public void setPersonal() {
        RESUME_1.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_4.getUuid()));
        assertEquals(RESUME_1.getSection(SectionType.PERSONAL).toString(), "uuid1 I'm good at studying )");
    }

    @Test
    public void setObjective() {
        RESUME_1.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_4.getUuid()));
        assertEquals(RESUME_1.getSection(SectionType.OBJECTIVE).toString(), "uuid1 Now - student BaseJava course.");
    }

    @Test
    public void setAchievement() {
        RESUME_1.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_4.getUuid()));
    }

    @Test
    public void setQualifications() {
        RESUME_1.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_4.getUuid()));
    }

    @Test
    public void setWorkExperience() {
        RESUME_1.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_4.getUuid()));
    }

    @Test
    public void setStudyExperience() {
        RESUME_1.setSection(SectionType.EDUCATION, DataSetter.setStudyExperienceSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.EDUCATION, DataSetter.setStudyExperienceSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.EDUCATION, DataSetter.setStudyExperienceSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.EDUCATION, DataSetter.setStudyExperienceSection(RESUME_4.getUuid()));
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}