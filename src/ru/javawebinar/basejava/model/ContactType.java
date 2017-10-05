package ru.javawebinar.basejava.model;

public enum ContactType {
    ADDRESS("Адрес"),
    EMAIL("e-mail"),
    WEBSITE("web site");


    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}