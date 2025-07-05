package com.example.mongodb.model;

import java.util.Objects;

// Same as Department, not a collection.
public class Subjects {

    private String name;
    private int marks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subjects subjects = (Subjects) o;
        return marks == subjects.marks && Objects.equals(name, subjects.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marks);
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
