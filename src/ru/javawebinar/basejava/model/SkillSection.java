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

}