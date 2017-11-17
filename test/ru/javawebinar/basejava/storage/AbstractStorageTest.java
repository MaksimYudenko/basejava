package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private static class DataSetter {
        static Section setPersonalSection(String uuid) {
            return new TextSection(uuid + " I'm good at studying )");
        }

        static Section setObjectiveSection(String uuid) {
            return new TextSection(uuid + " Now - student BaseJava course.");
        }

        static Section setAchievementSection(String uuid) {
            List<String> achievementList = new ArrayList<>();
            achievementList.add(uuid + "Found site http://javaops.ru/");
            achievementList.add(uuid + "Have practice in BaseJava");
            achievementList.add(uuid + "Passed the sixth level");
            return new ListSection(achievementList);
        }

        static Section setQualificationsSection(String uuid) {
            List<String> qualificationsList = new ArrayList<>();
            qualificationsList.add(uuid + "JavaRush - passed several levels");
            qualificationsList.add(uuid + "BaseJava - passed six levels");
            return new ListSection(qualificationsList);
        }

        static Section setWorkExperienceSection(String uuid) {
            List<Organization> workPlacements = new ArrayList<>();
            Organization workPlacement1 = new Organization(uuid + " Org1",
                    "www.org1.com", "function 1", "ORGANIZATION-1", "experience1",
                    LocalDate.of(2008, 7, 1), LocalDate.of(2010, 7, 1));
            Organization workPlacement2 = new Organization(uuid + " Org2",
                    "www.org2.com", "function 2", "ORGANIZATION-2", "experience2",
                    LocalDate.of(2010, 7, 2), LocalDate.of(2014, 7, 2));
            workPlacements.add(workPlacement1);
            workPlacements.add(workPlacement2);
            return new OrganizationSection(workPlacements);
        }

        static Section setStudyExperienceSection(String uuid) {
            List<Organization> studyPlacements = new ArrayList<>();
            Organization studyPlacement1 = new Organization(uuid + "Stud1",
                    "www.stud1.com", "studying 1", "StudOrg-1", "learnExp1",
                    LocalDate.of(2003, 7, 1), LocalDate.of(2006, 7, 1));
            Organization studyPlacement2 = new Organization(uuid + "Stud2",
                    "www.stud2.com", "studying 2", "StudOrg-2", "learnExp2",
                    LocalDate.of(2006, 7, 2), LocalDate.of(2008, 7, 1));
            studyPlacement2.addBusyPeriod("studying 3", "StudOrg-3", "learnExp3"
                    ,LocalDate.of(2004, 7, 1), LocalDate.of(2006, 7, 1));
            studyPlacements.add(studyPlacement1);
            studyPlacements.add(studyPlacement2);
            return new OrganizationSection(studyPlacements);
        }
    }

    private void setPhoneNumber() {
        RESUME_1.setContact(ContactType.PHONE, "1234567");
        RESUME_2.setContact(ContactType.PHONE, "2234567");
        RESUME_3.setContact(ContactType.PHONE, "3234567");
        RESUME_4.setContact(ContactType.PHONE, "4234567");
    }

    private void setMail() {
        RESUME_1.setContact(ContactType.MAIL, "yudenkomaksim@gmail.com");
        RESUME_2.setContact(ContactType.MAIL, "abc@gmail.com");
        RESUME_3.setContact(ContactType.MAIL, "def@gmail.com");
        RESUME_4.setContact(ContactType.MAIL, "ghi@gmail.com");
    }

    private void setPersonal() {
        RESUME_1.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.PERSONAL, DataSetter.setPersonalSection(RESUME_4.getUuid()));
    }

    private void setObjective() {
        RESUME_1.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.OBJECTIVE, DataSetter.setObjectiveSection(RESUME_4.getUuid()));
    }

    private void setAchievement() {
        RESUME_1.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.ACHIEVEMENT, DataSetter.setAchievementSection(RESUME_4.getUuid()));
    }

    private void setQualifications() {
        RESUME_1.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.QUALIFICATIONS, DataSetter.setQualificationsSection(RESUME_4.getUuid()));
    }

    private void setWorkExperience() {
        RESUME_1.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_1.getUuid()));
        RESUME_2.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_2.getUuid()));
        RESUME_3.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_3.getUuid()));
        RESUME_4.setSection(SectionType.EXPERIENCE, DataSetter.setWorkExperienceSection(RESUME_4.getUuid()));
    }

    private void setStudyExperience() {
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
        setPhoneNumber();
        setMail();
        setPersonal();
        setObjective();
        setAchievement();
        setQualifications();
        setWorkExperience();
        setStudyExperience();
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