package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Organization {
    private final Link homePage;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private Map<LocalDate, LocalDate> workPeriod = new TreeMap<>();
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate startDate,
                        LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        workPeriod.put(startDate, endDate);
    }

    public void addBusyPeriod(LocalDate startDateAdd, LocalDate endDateAdd) {
        Objects.requireNonNull(startDateAdd, "startDateAdd must not be null");
        Objects.requireNonNull(endDateAdd, "endDateAdd must not be null");
        workPeriod.put(startDateAdd, endDateAdd);
    }

    protected Map<LocalDate, LocalDate> getWorkPeriod() {
        return workPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) :
                that.description == null;

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String description1 = "Organization{" + "homePage=" + homePage;
        String period = ", startDate=" + startDate + ", endDate=" + endDate;
        String description2 = ", title='" + title + '\'' + ", description='"
                + description + '\'' + '}' + "\n";
        if (!workPeriod.isEmpty()) {
            StringBuilder duration = new StringBuilder();
            for (Map.Entry wp : getWorkPeriod().entrySet()) {
                duration.append(", startDate=").append(wp.getKey()).
                        append(", endDate=").append(wp.getValue());
            }
            period = duration.toString();
        }
        return description1 + period + description2;
    }
}