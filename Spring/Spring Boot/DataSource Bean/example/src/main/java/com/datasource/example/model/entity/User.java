/*
Resources:
[1] https://chat.openai.com/c/5971add2-c40c-4fc2-84ef-dc234fa90400 [search phrase: "and Hibernate work with entity classes"]
*/

package com.datasource.example.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;
    private final String lastname;

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    protected User() { //a protected no-args constructor in compliance to the JPA (Hibernate)[1]
        this.name  = null;
        this.lastname = null;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getLastname() { return lastname; }

    @Override
    public String toString() {
        return "[name=" + name + ",lastname=" + lastname  + "]";
    }
}
