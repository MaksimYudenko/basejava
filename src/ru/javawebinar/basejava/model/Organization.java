package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final List<Position> positions = new ArrayList<>();
    private final Position position;


    public Organization(String name, String url, String function,
                        String title, String description, LocalDate startDate,
                        LocalDate endDate) {
        this.homePage = new Link(name, url);
        this.position = new Position(function, title, description, startDate,
                endDate);
        this.positions.add(this.position);
    }

    public class Position {
        private String function;
        private String title;
        private String description;
        private LocalDate startDate, endDate;

        Position(String function, String title, String description,
                 LocalDate startDate, LocalDate endDate) {
            Objects.requireNonNull(function, "function must not be null");
            Objects.requireNonNull(title, "title must not be null");
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            this.function = function;
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return ", function '" + function + '\'' + ", title '" + title + '\''
                    + ", description '" + description + '\'' + ", period from "
                    + startDate + " to " + endDate;
        }
    }

    public void addBusyPeriod(String functionAdd, String titleAdd,
                              String descriptionAdd, LocalDate startDateAdd,
                              LocalDate endDateAdd) {
        Objects.requireNonNull(functionAdd, "function must not be null");
        Objects.requireNonNull(titleAdd, "title must not be null");
        Objects.requireNonNull(descriptionAdd, "startDate must not be null");
        Objects.requireNonNull(startDateAdd, "startDateAdd must not be null");
        Objects.requireNonNull(endDateAdd, "endDateAdd must not be null");
        positions.add(new Position(functionAdd, titleAdd, descriptionAdd,
                startDateAdd, endDateAdd));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!position.startDate.equals(that.position.startDate)) return false;
        if (!position.endDate.equals(that.position.endDate)) return false;
        if (!position.title.equals(that.position.title)) return false;
        return position.description != null ? position.description.equals
                (that.position.description) : that.position.description == null;

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + position.startDate.hashCode();
        result = 31 * result + position.endDate.hashCode();
        result = 31 * result + position.title.hashCode();
        result = 31 * result + (position.description != null ? position.
                description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String positionsText = "";
        if (positions.size() > 1) {
            for (Position p : positions) {
                positionsText += p;
            }
        } else {
            positionsText = position.toString();
        }
        return "Organization " + homePage.getName() + ", homePage " +
                homePage.getUrl() + positionsText + "\n";
    }

}