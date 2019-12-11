package Models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends SortedObject implements Serializable {

    @Expose
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Expose
    @Column(name = "email", unique = true, updatable = false)
    private String email;

    @Expose
    @Column(name = "password")
    private String password;

    @Expose
    @Column(name = "firstName")
    private String firstName;

    @Expose
    @Column(name = "lastName")
    private String lastName;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updatedAt;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deletedAT")
    private Date deletedAt;

//    @Expose
//    private String name;

//    @Expose
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateTime;

    public User () {

    }


    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = new Date();
        this.dateTime = createdAt;
        this.name = lastName + " " + firstName;
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

    public String getName() {
        return name;
    }

    public Date getDateTime() {
        return dateTime;
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
