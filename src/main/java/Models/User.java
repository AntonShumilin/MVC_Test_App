package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", unique = true, updatable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "deletedAT")
    private Date deletedAt;

    public User () {

    }





    public HashMap <String, CheckWeb> userChecks = new HashMap<String, CheckWeb>();

    public void addCheck (CheckWeb checkWeb) {
        userChecks.put(checkWeb.document.receipt.dateTime + checkWeb.document.receipt.fiscalSign, checkWeb);
    }
    public CheckWeb getCheckByFS (String fs) {
        return userChecks.get(fs);
    }


    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

        public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

    public long getId() { return id; }

    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
