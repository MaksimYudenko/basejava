package ru.javawebinar.basejava.model;

import java.lang.reflect.Field;
import java.util.*;

public class Resume implements Comparable<Resume> {

    private String uuid;
    private String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid + " (" + fullName + ')';
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }


    public final class ResumeContacts {

        private String address, email, webSite;

        private Map<String, String> contacts = new TreeMap<>();

        public void setContacts() {
            contacts.put("address", address);
            contacts.put("email", email);
            contacts.put("webSite", webSite);
        }

        public String getContacts(String contact) {
            String getContact = "Contact \"" + contact + "\" is unknown.";
            if (contacts.containsKey(contact)) {
                if (contacts.get(contact) == null) {
                    getContact = "Contact \"" + contact + "\" is not defined.";
                } else {
                    switch (contact) {
                        case "address":
                            getContact = contact + " is " + contacts.get("address");
                            break;
                        case "email":
                            getContact = contact + " is " + contacts.get("email");
                            break;
                        case "webSite":
                            getContact = contact + " is " + contacts.get("webSite");
                            break;
                    }
                }
            }
            return getContact;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setWebSite(String webSite) {
            this.webSite = webSite;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String key : contacts.keySet()) {
                String k = contacts.get(key);
                if (k == null) {
                    sb.append("\n").append(key).append(" - unknown");
                } else {
                    sb.append("\n").append(key).append(" - ").append(k);
                }
            }
            return sb.toString();
        }

        public String getFields() {
            StringBuilder sb = new StringBuilder();
            Field[] fields = ResumeContacts.class.getDeclaredFields();
            for (int i = 0; i < fields.length - 2; i++) {
                sb.append(fields[i].getName()).append(" ");
            }
            return "Existing contacts: " + sb.toString();
        }
    }

    public ResumeContacts createResumeContacts() {
        return new ResumeContacts();
    }

    public final class ResumeSections {

        private List<String> personal = new ArrayList<>();
        private List<String> objective = new ArrayList<>();
        private List<String> achievement = new ArrayList<>();
        private List<String> qualifications = new ArrayList<>();
        private List<String> experience = new ArrayList<>();
        private List<String> education = new ArrayList<>();
        private final EnumMap<SectionType, List> typesMap = new EnumMap<>(SectionType.class);

        public void setPersonal(String personalInfo) {
            this.personal.add(personalInfo);
        }

        public void setObjective(String objectiveInfo) {
            this.objective.add(objectiveInfo);
        }

        public void setAchievement(String achievementInfo) {
            this.achievement.add(achievementInfo);
        }

        public void setQualifications(String qualificationsInfo) {
            this.qualifications.add(qualificationsInfo);
        }

        public void setExperience(String experienceInfo) {
            this.experience.add(experienceInfo);
        }

        public void setEducation(String educationInfo) {
            this.education.add(educationInfo);
        }

        public void setTypesMap() {
            typesMap.put(SectionType.PERSONAL, personal);
            typesMap.put(SectionType.OBJECTIVE, objective);
            typesMap.put(SectionType.ACHIEVEMENT, achievement);
            typesMap.put(SectionType.QUALIFICATIONS, qualifications);
            typesMap.put(SectionType.EXPERIENCE, experience);
            typesMap.put(SectionType.EDUCATION, education);
        }

        public String getSectionNames() {
            StringBuilder sb = new StringBuilder();
            for (SectionType st : SectionType.values()) {
                sb.append(st).append(" ");
            }
            return "Existing sections: " + sb.toString();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<SectionType, List> entry : typesMap.entrySet()) {
                sb.append(entry.getKey()).append("\n")
                        .append(typesMap.get(entry.getKey())).append("\n");
            }
            return "\nSectionInfo:\n" + sb.toString();
        }
    }

    public ResumeSections createResumeSections() {
        return new ResumeSections();
    }
}