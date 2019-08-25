package Models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {

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
    @Column(name = "createdAt")
    private Date createdAt;

    @Expose
    @Column(name = "updatedAt")
    private Date updatedAt;

    @Expose
    @Column(name = "deletedAT")
    private Date deletedAt;

    public User () {

    }





//    public HashMap <String, Check> userChecks = new HashMap<String, Check>();
//
//    public void addCheck (Check check) {
//        userChecks.put(check.document.receipt.dateTime + check.document.receipt.fiscalSign, check);
//    }
//    public Check getCheckByFS (String fs) {
//        return userChecks.get(fs);
//    }


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
