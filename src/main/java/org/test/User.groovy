package org.test;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {
    public static final String USER = "user"
    public static final String PASSWORD = "password"
    public static final String NAME = "name"
    public static final String MAIL = "mail"


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id

    @NotNull
    private String user
    private String password
    private String name
    private String mail

    public User(String user, String password, String name, String mail) {
        this.user = user
        this.name = name
        this.mail = mail
        this.password = password
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name
    }

    void setUser(String user) {
        this.user = user
    }

    public void setPassword(String password) {
        this.password = password
    }

    void setMail(String mail) {
        this.mail = mail
    }

    String getMail() {
        return mail
    }

    public String getName() {
        return name
    }

    String getUser() {
        return user
    }

    public String getPassword() {
        return password
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}
