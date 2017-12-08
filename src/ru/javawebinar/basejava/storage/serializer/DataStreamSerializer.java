package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                if (sectionType.equals(SectionType.PERSONAL)) {
                    dos.writeUTF(sectionType.toString());
                    TextSection textSection = (TextSection) entry.getValue();
                    dos.writeUTF(textSection.getContent());
                }
                if (sectionType.equals(SectionType.OBJECTIVE)) {
                    dos.writeUTF(sectionType.toString());
                    TextSection textSection = (TextSection) entry.getValue();
                    dos.writeUTF(textSection.getContent());
                }
                if (sectionType.equals(SectionType.ACHIEVEMENT)) {
                    dos.writeUTF(sectionType.toString());
                    ListSection listSection = (ListSection) entry.getValue();
                    dos.writeInt(listSection.getItems().size());
                    for (String item : listSection.getItems()) {
                        dos.writeUTF(item);
                    }
                }
                if (sectionType.equals(SectionType.QUALIFICATIONS)) {
                    dos.writeUTF(sectionType.toString());
                    ListSection listSection = (ListSection) entry.getValue();
                    dos.writeInt(listSection.getItems().size());
                    for (String item : listSection.getItems()) {
                        dos.writeUTF(item);
                    }
                }
                if (sectionType.equals(SectionType.EXPERIENCE)) {
                    dos.writeUTF(sectionType.toString());
                    OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                    List<Organization> organizations = organizationSection.getOrganizations();
                    dos.writeInt(organizations.size());
                    for (Organization org : organizations) {
                        dos.writeUTF(org.getHomePage().getName());
                        dos.writeUTF(org.getHomePage().getUrl());
                        dos.writeInt(org.getPositions().size());
                        for (Organization.Position position : org.getPositions()) {
                            String startDateToString = position.getStartDate().toString();
                            dos.writeUTF(startDateToString.substring(0, 4));
                            dos.writeUTF(startDateToString.substring(5, 7));
                            dos.writeUTF(startDateToString.substring(8, 10));
                            String endDateToString = position.getEndDate().toString();
                            dos.writeUTF(endDateToString.substring(0, 4));
                            dos.writeUTF(endDateToString.substring(5, 7));
                            dos.writeUTF(endDateToString.substring(8, 10));
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        }
                    }
                }
                if (sectionType.equals(SectionType.EDUCATION)) {
                    dos.writeUTF(sectionType.toString());
                    OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                    for (Organization org : organizationSection.getOrganizations()) {
                        dos.writeUTF(org.getHomePage().getName());
                        dos.writeUTF(org.getHomePage().getUrl());
                        for (Organization.Position position : org.getPositions()) {
                            String startDateToString = position.getStartDate().toString();
                            dos.writeUTF(startDateToString.substring(0, 4));
                            dos.writeUTF(startDateToString.substring(5, 7));
                            dos.writeUTF(startDateToString.substring(8, 10));
                            String endDateToString = position.getEndDate().toString();
                            dos.writeUTF(endDateToString.substring(0, 4));
                            dos.writeUTF(endDateToString.substring(5, 7));
                            dos.writeUTF(endDateToString.substring(8, 10));
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                if (dis.readUTF().equalsIgnoreCase("PERSONAL")) {
                    resume.addSection(SectionType.PERSONAL, new TextSection(dis.readUTF()));
                }
                if (dis.readUTF().equalsIgnoreCase("OBJECTIVE")) {
                    resume.addSection(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
                }
                if (dis.readUTF().equalsIgnoreCase("ACHIEVEMENT")) {
                    int achievementQuantity = dis.readInt();
                    List<String> items = new ArrayList<>(achievementQuantity);
                    for (int j = 0; j < achievementQuantity; j++) {
                        items.add(dis.readUTF());
                    }
                    resume.addSection(SectionType.ACHIEVEMENT, new ListSection(items));
                }
                if (dis.readUTF().equalsIgnoreCase("QUALIFICATIONS")) {
                    int qualificationQuantity = dis.readInt();
                    List<String> items = new ArrayList<>(qualificationQuantity);
                    for (int j = 0; j < qualificationQuantity; j++) {
                        items.add(dis.readUTF());
                    }
                    resume.addSection(SectionType.QUALIFICATIONS, new ListSection(items));
                }
                if (dis.readUTF().equalsIgnoreCase("EXPERIENCE")) {
                    int experienceQuantity = dis.readInt();
                    List<Organization> organizations = new ArrayList<>(experienceQuantity);
                    List<Organization.Position> positions = new ArrayList<>();
                    for (int j = 0; j < experienceQuantity; j++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        Link homePage = new Link(name, url);
                        Organization organization = new Organization(homePage, positions);
                        int positionQuantity = dis.readInt();
                        Organization.Position position;
                        for (int k = 0; k < positionQuantity; k++) {
                            int startYear = dis.readInt();
                            Month startMonth = Month.of(dis.readInt());
                            int startDay = dis.readInt();
                            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
                            int endYear = dis.readInt();
                            Month endMonth = Month.of(dis.readInt());
                            int endDay = dis.readInt();
                            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
                            String title = dis.readUTF();
                            String description = dis.readUTF();
                            position = new Organization.Position(startDate, endDate, title, description);
                            positions.add(position);
                        }
                        organizations.add(organization);
                    }
                    resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));
                }
                if (dis.readUTF().equalsIgnoreCase("EDUCATION")) {
                    int educationQuantity = dis.readInt();
                    List<Organization> organizations = new ArrayList<>(educationQuantity);
                    List<Organization.Position> positions = new ArrayList<>();
                    for (int j = 0; j < educationQuantity; j++) {
                        String name = dis.readUTF();
                        String url = dis.readUTF();
                        Link homePage = new Link(name, url);
                        Organization organization = new Organization(homePage, positions);
                        int positionQuantity = dis.readInt();
                        Organization.Position position;
                        for (int k = 0; k < positionQuantity; k++) {
                            int startYear = dis.readInt();
                            Month startMonth = Month.of(dis.readInt());
                            int startDay = dis.readInt();
                            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
                            int endYear = dis.readInt();
                            Month endMonth = Month.of(dis.readInt());
                            int endDay = dis.readInt();
                            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
                            String title = dis.readUTF();
                            String description = dis.readUTF();
                            position = new Organization.Position(startDate, endDate, title, description);
                            positions.add(position);
                        }
                        organizations.add(organization);
                    }
                    resume.addSection(SectionType.EDUCATION, new OrganizationSection(organizations));
                }

            }
            return resume;
        }
    }
}
