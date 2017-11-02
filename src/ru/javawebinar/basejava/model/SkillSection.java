package ru.javawebinar.basejava.model;

import java.util.List;

public class SkillSection extends Section {

    private List<Organization> organizations;

    public SkillSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization s : organizations)
            sb.append(s.getName()).append(" - ").append(s.getPeriod().toString()).append(". ");
        return sb.toString();
    }
}