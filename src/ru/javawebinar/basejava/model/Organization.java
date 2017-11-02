package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    private String name;
    private Period period;

    private List<String> experience = new ArrayList<>();

    public Organization(String name, LocalDate begin, LocalDate end, List<String> experience) {
        this.name = name;
        this.period = Period.between(begin, end);
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public Period getPeriod() {
        return period;
    }

    public List<String> getExperience() {
        return experience;
    }

}