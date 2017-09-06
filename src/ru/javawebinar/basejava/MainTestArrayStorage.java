package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.*;

public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) {

        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        r1.setFullName("Petr_Sidorov (resume1)");
        r2.setFullName("Ivan_Petrov (resume2)");
        r3.setFullName("Sidor_Ivanoff (resume3)");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();
        System.out.println();
        ARRAY_STORAGE.update(r3);
        System.out.println("update r3: " + ARRAY_STORAGE.get(r3.getUuid()));
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r.getFullName());
        }
    }
}