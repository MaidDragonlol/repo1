package com.example.demo.Domain;

import java.util.List;

public class User {
    private int id;
    private String name;
    private IDCard iDcard;
    private List<Phone> phones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IDCard getiDcard() {
        return iDcard;
    }

    public void setiDcard(IDCard iDcard) {
        this.iDcard = iDcard;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
