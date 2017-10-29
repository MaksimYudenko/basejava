package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    private String name;
    private LocalDate period;
    private List<String> experience = new ArrayList<>();

    public Organization(String name, LocalDate period, List<String> experience) {
        this.name = name;
        this.period = period;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public List<String> getExperience() {
        return experience;
    }

}