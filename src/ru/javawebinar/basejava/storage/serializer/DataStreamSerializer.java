package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.model.Organization.Position;
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
        writeSectionType(dos, sectionType);
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                dos.writeUTF(((TextSection) entry.getValue()).getContent());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                ListSection listSection = (ListSection) entry.getValue();
                writeSize(dos, listSection.getItems());
                for (String item : listSection.getItems()) {
                    dos.writeUTF(item);
                }
                break;
            case EXPERIENCE:
            case EDUCATION:
                List<Organization> organizations = ((OrganizationSection)
                        entry.getValue()).getOrganizations();
                writeSize(dos, organizations);
                for (Organization org : organizations) {
                    dos.writeUTF(org.getHomePage().getName());
                    dos.writeUTF(org.getHomePage().getUrl());
                    writeSize(dos, org.getPositions());
                    for (Position position : org.getPositions()) {
                        writeDate(dos, position.getStartDate().toString());
                        writeDate(dos, position.getEndDate().toString());
                        dos.writeUTF(position.getTitle());
                        dos.writeUTF(position.getDescription());
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
                resume.addSection(SectionType.valueOf(sectionType)
                        , new TextSection(dis.readUTF()));
                break;
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                int quantity = readSize(dis);
                List<String> items = new ArrayList<>(quantity);
                for (int j = 0; j < quantity; j++) items.add(dis.readUTF());
                resume.addSection(SectionType.valueOf(sectionType), new ListSection(items));
                break;
            case "EXPERIENCE":
            case "EDUCATION":
                int orgQuantity = readSize(dis);
                List<Organization> organizations = new ArrayList<>(orgQuantity);
                for (int j = 0; j < orgQuantity; j++) {
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    int posQuantity = readSize(dis);
                    List<Position> positions = new ArrayList<>(posQuantity);
                    for (int k = 0; k < posQuantity; k++) {
                        positions.add(new Position(localDate(dis.readInt(),
                                dis.readInt()), localDate(dis.readInt(),
                                dis.readInt()), dis.readUTF(), dis.readUTF()));
                    }
                    organizations.add(new Organization(new Link(name, url), positions));
                }
                resume.addSection(SectionType.valueOf(sectionType)
                        , new OrganizationSection(organizations));
                break;
        }
    }

}