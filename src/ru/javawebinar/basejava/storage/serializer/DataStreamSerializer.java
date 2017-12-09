package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    private Resume resume;

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeSize(dos, contacts.entrySet());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            writeSize(dos, sections.entrySet());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                writeSection(dos, entry);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);
            int size = readSize(dis);
            for (int i = 0; i < size; i++)
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            size = readSize(dis);
            for (int i = 0; i < size; i++) readSection(dis);
            return resume;
        }
    }

    private void writeSize(DataOutputStream dos, Collection collection) throws IOException {
        dos.writeInt(collection.size());
    }

    private void writeSectionType(DataOutputStream dos, SectionType sectionType) throws IOException {
        dos.writeUTF(sectionType.toString());
    }

    private void writeDate(DataOutputStream dos, String dateToString) throws IOException {
        if (dateToString.equalsIgnoreCase(DateUtil.NOW.toString())) {
            dos.writeInt(0);
            dos.writeInt(0);
        } else {
            dos.writeInt(Integer.parseInt(dateToString.substring(0, 4)));
            dos.writeInt(Integer.parseInt(dateToString.substring(5, 7)));
        }
    }

    private void writeSection(DataOutputStream dos, Map.Entry entry) throws IOException {
        SectionType sectionType = (SectionType) entry.getKey();
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                writeSectionType(dos, sectionType);
                TextSection textSection = (TextSection) entry.getValue();
                dos.writeUTF(textSection.getContent());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeSectionType(dos, sectionType);
                ListSection listSection = (ListSection) entry.getValue();
                writeSize(dos, listSection.getItems());
                for (String item : listSection.getItems()) {
                    dos.writeUTF(item);
                }
                break;
            case EXPERIENCE:
            case EDUCATION:
                writeSectionType(dos, sectionType);
                OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                List<Organization> organizations = organizationSection.getOrganizations();
                writeSize(dos, organizations);
                for (Organization org : organizations) {
                    dos.writeUTF(org.getHomePage().getName());
                    String url = org.getHomePage().getUrl();
                    if (url.equals("null")) {
                        dos.writeUTF("");
                    } else {
                        dos.writeUTF(url);
                    }
                    writeSize(dos, org.getPositions());
                    for (Organization.Position position : org.getPositions()) {
                        String startDateToString = position.getStartDate().toString();
                        String endDateToString = position.getEndDate().toString();
                        writeDate(dos, startDateToString);
                        writeDate(dos, endDateToString);
                        dos.writeUTF(position.getTitle());
                        String description = position.getDescription();
                        if (description == null) {
                            dos.writeUTF("");
                        } else {
                            dos.writeUTF(description);
                        }
                    }
                }
                break;
        }
    }

    private int readSize(DataInputStream dis) throws IOException {
        return dis.readInt();
    }

    private LocalDate localDate(int year, int month) {
        if (year == 0) return DateUtil.NOW;
        return LocalDate.of(year, month, 1);
    }

    private void readSection(DataInputStream dis) throws IOException {
        String sectionType = dis.readUTF();
        switch (sectionType) {
            case "PERSONAL":
            case "OBJECTIVE":
                resume.addSection(SectionType.valueOf(sectionType), new TextSection(dis.readUTF()));
                break;
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                int quantity = dis.readInt();
                List<String> items = new ArrayList<>();
                for (int j = 0; j < quantity; j++) items.add(dis.readUTF());
                resume.addSection(SectionType.valueOf(sectionType), new ListSection(items));
                break;
            case "EXPERIENCE":
            case "EDUCATION":
                quantity = dis.readInt();
                List<Organization> organizations = new ArrayList<>();
                List<Organization.Position> positions = new ArrayList<>();
                for (int j = 0; j < quantity; j++) {
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    Link homePage = new Link(name, url);
                    int positionQuantity = dis.readInt();
                    Organization organization = new Organization(homePage, positions);
                    Organization.Position position;
                    for (int k = 0; k < positionQuantity; k++) {
                        LocalDate startDate = localDate(dis.readInt(), dis.readInt());
                        LocalDate endDate = localDate(dis.readInt(), dis.readInt());
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        position = new Organization.Position(startDate, endDate, title, description);
                        positions.add(position);
                    }
                    organizations.add(organization);
                }
                resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(organizations));
                break;
        }
    }

}