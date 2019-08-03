package Models;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public User(String login, String password, String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

    public User(String login) {
        this.login = login;
        this.password = login;
        this.firstName = login;
        this.lastName = login;
        this.email = "email";
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }
}
