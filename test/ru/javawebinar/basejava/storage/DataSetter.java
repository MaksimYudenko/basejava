package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DataSetter {

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
        Organization workPlacement1 = new Organization(uuid + " Org1", "www.org1.com",
                LocalDate.of(2008, 7, 1), LocalDate.of(2010, 7, 1),
                "ORGANIZATION-1", "experience1");
        Organization workPlacement2 = new Organization(uuid + " Org2", "www.org2.com",
                LocalDate.of(2010, 7, 2), LocalDate.of(2014, 7, 2),
                "ORGANIZATION-2", "experience2");
        workPlacements.add(workPlacement1);
        workPlacements.add(workPlacement2);
        return new OrganizationSection(workPlacements);
    }

    static Section setStudyExperienceSection(String uuid) {
        List<Organization> studyPlacements = new ArrayList<>();
        Organization studyPlacement1 = new Organization("Stud1", "www.stud1.com",
                LocalDate.of(2003, 7, 1), LocalDate.of(2006, 7, 1),
                uuid + "StudOrg-1", "learnExp1");
        Organization studyPlacement2 = new Organization("Stud2", "www.stud2.com",
                LocalDate.of(2006, 7, 2), LocalDate.of(2008, 7, 1),
                uuid + "StudOrg-2", "learnExp2");
        studyPlacement2.addBusyPeriod(LocalDate.of(2004, 7, 1),
                LocalDate.of(2006, 7, 1));
        studyPlacements.add(studyPlacement1);
        studyPlacements.add(studyPlacement2);
        return new OrganizationSection(studyPlacements);
    }

}