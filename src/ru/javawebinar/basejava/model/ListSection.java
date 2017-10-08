package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {

    private List<String> block;

    public ListSection(List<String> block) {
        this.block = block;
    }

    public List<String> getItems() {
        return block;
    }
}