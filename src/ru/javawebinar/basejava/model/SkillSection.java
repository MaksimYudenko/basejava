package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillSection extends Section {

    private Map<String, Map<String, List<String>>> organizations;

    public SkillSection(String orgName, String period, List<String> experience) {
        Map<String, List<String>> orgExperience = new HashMap<>();
        orgExperience.put(period, experience);
        organizations.put(orgName, orgExperience);
    }

    public Map<String, Map<String, List<String>>> getOrganizations() {
        return organizations;
    }

}