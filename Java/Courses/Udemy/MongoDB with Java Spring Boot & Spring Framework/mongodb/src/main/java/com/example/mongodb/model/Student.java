package com.example.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String name;
    private String email;

    private Department department;
    private List<Subjects> subjects;


    /* You annotate with @Transient a field you won't persist or retrieve from the database.
    *  use it when you want to write some logic for this field.
    *  BUT IT WILL BE SHOWN IN AN API CALL IF YOU ADD GETTER WITH THE LOGIC INSIDE (OR NOT)!
    * */
    @Transient
    private double average;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public double getAverage() {
        return 3.14;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(department, student.department) && Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, department, subjects);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", subjects=" + subjects +
                '}';
    }
}
