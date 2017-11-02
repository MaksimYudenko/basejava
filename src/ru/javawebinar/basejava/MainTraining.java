package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainTraining {
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
        List<String> expirience1 = new ArrayList<>();
        expirience1.add("exp11");
        expirience1.add("exp12");
        expirience1.add("exp13");
        List<String> experience2 = new ArrayList<>();
        experience2.add("exp21");
        experience2.add("exp22");
        experience2.add("exp23");
        Organization workPlacement1 = new Organization("Org1", LocalDate.of(2008, 07, 01), LocalDate.of(2010, 07, 01), expirience1);
        Organization workPlacement2 = new Organization("Org2", LocalDate.of(2010, 07, 02), LocalDate.of(2014, 07, 02), experience2);
        workPlacements.add(workPlacement1);
        workPlacements.add(workPlacement2);
        List<Organization> studyPlacements = new ArrayList<>();
        List<String> learning1 = new ArrayList<>();
        learning1.add("learn11");
        learning1.add("learn12");
        learning1.add("learn13");
        List<String> learning2 = new ArrayList<>();
        learning2.add("learn21");
        learning2.add("learn22");
        learning2.add("learn23");
        Organization studyPlacement1 = new Organization("Stud1", LocalDate.of(2003, 07, 01), LocalDate.of(2006, 07, 01), learning1);
        Organization studyPlacement2 = new Organization("Stud2", LocalDate.of(2006, 07, 02), LocalDate.of(2008, 07, 02), learning2);
        studyPlacements.add(studyPlacement1);
        studyPlacements.add(studyPlacement2);


        Section personal = new TextSection("I'm good at studying )");
        Section objective = new TextSection("Now - student BaseJava course.");
        Section achievement = new ListSection(achievementList);
        Section qualifications = new ListSection(qualificationsList);
        Section experience = new SkillSection(workPlacements);
        Section education = new SkillSection(studyPlacements);


        r.setContact(ContactType.ADDRESS, "Belarus, Gomel");
        r.setContact(ContactType.EMAIL, "yudenkomaksim@gmail.com");
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