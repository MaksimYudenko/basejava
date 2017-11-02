package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {

    private List<String> block;

    public ListSection(List<String> block) {
        this.block = block;
    }

    public List<String> getBlock() {
        return block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s:block) sb.append(s).append(". ");
        return sb.toString();
    }
}