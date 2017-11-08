package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainTry {
    public static void main(String[] args) {
        Resume r = new Resume("ID 1", "Maksim Yudenko");
        List<String> achievementList = new ArrayList<>();
        achievementList.add("Found site http://javaops.ru/");
        achievementList.add("Have practice in BaseJava");
        achievementList.add("Passed the sixth level");
        List<String> qualificationsList = new ArrayList<>();
        qualificationsList.add("JavaRush - passed several levels");
        qualificationsList.add("BaseJava - passed six levels");
        List<Organization> workPlacements = new ArrayList<>();
        Organization workPlacement1 = new Organization("Org1", "www.org1.com",
                LocalDate.of(2008, 7, 1), LocalDate.of(2010, 7, 1),
                "ORGANIZATION-1", "experience1");
        Organization workPlacement2 = new Organization("Org2", "www.org2.com",
                LocalDate.of(2010, 7, 2), LocalDate.of(2014, 7, 2),
                "ORGANIZATION-2", "experience2");
        workPlacements.add(workPlacement1);
        workPlacements.add(workPlacement2);
        List<Organization> studyPlacements = new ArrayList<>();
        Organization studyPlacement1 = new Organization("Stud1", "www.stud1.com",
                LocalDate.of(2003, 7, 1), LocalDate.of(2006, 7, 1),
                "StudOrg-1", "learnExp1");
        Organization studyPlacement2 = new Organization("Stud2", "www.stud2.com",
                LocalDate.of(2006, 7, 2), LocalDate.of(2008, 7, 1),
                "StudOrg-2", "learnExp2");
        studyPlacement2.addBusyPeriod(LocalDate.of(2004, 7, 1),
                LocalDate.of(2006, 7, 1));
        studyPlacements.add(studyPlacement1);
        studyPlacements.add(studyPlacement2);

        Section personal = new TextSection("I'm good at studying )");
        Section objective = new TextSection("Now - student BaseJava course.");
        Section achievement = new ListSection(achievementList);
        Section qualifications = new ListSection(qualificationsList);
        Section experience = new OrganizationSection(workPlacements);
        Section education = new OrganizationSection(studyPlacements);

        r.setContact(ContactType.PHONE, "1234567");
        r.setContact(ContactType.MAIL, "yudenkomaksim@gmail.com");
        r.setSection(SectionType.PERSONAL, personal);
        r.setSection(SectionType.OBJECTIVE, objective);
        r.setSection(SectionType.ACHIEVEMENT, achievement);
        r.setSection(SectionType.QUALIFICATIONS, qualifications);
        r.setSection(SectionType.EXPERIENCE, experience);
        r.setSection(SectionType.EDUCATION, education);
        for (Map.Entry s : r.getSections().entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}