package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final LocalDate startDate, endDate;
    private LocalDate startDateAdd, endDateAdd;
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
//        workPeriod.put(startDate, endDate);
    }

    public void addBusyPeriod(LocalDate startDateAdd, LocalDate endDateAdd) {
        Objects.requireNonNull(startDateAdd, "startDateAdd must not be null");
        Objects.requireNonNull(endDateAdd, "endDateAdd must not be null");
        this.startDateAdd = startDateAdd;
        this.endDateAdd = endDateAdd;
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
        String firstPeriod = ", startDate=" + startDate + ", endDate=" + endDate;
        String otherPeriod = "";
        String description2 = ", title='" + title + '\'' + ", description='"
                + description + '\'' + '}' + "\n";
        if (startDateAdd != null && endDateAdd != null) {
            StringBuilder duration = new StringBuilder();
            duration.append(", startDate=").append(startDateAdd).
                    append(", endDate=").append(endDateAdd);
            otherPeriod = duration.toString();
        }
        return description1 + firstPeriod + otherPeriod + description2;
    }

}