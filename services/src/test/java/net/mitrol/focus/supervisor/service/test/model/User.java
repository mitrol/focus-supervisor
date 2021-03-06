package net.mitrol.focus.supervisor.service.test.model;

import java.io.Serializable;

/**
 * This class is only test and the future should be delete
 */
public class User implements Serializable {

    private String id;

    private String name;

    private String lastname;

    private String mail;

    public User() {
    }

    public User(String id, String name, String lastname, String mail) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
    }

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
