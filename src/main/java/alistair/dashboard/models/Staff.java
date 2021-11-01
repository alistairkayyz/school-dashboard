package alistair.dashboard.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table
public class Staff {
    @Id
    @GeneratedValue
    @NotNull(message = "ID cannot be null")
    private long id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Firstname cannot be null")
    private String firstname;

    @NotNull(message = "Surname cannot be null")
    private String surname;

    @NotNull(message = "Gender cannot be null")
    private String gender;

    @NotNull(message = "Cellphone cannot be null")
    private String cellphone;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Designation cannot be null")
    private String designation;

    @NotNull(message = "RegDate cannot be null")
    private Date regDate;

    public Staff() {
    }

    public Staff(long id, String title, String firstname, String surname, String gender, String cellphone,
                 String email, String designation, Date regDate) {
        this.id = id;
        this.title = title;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.cellphone = cellphone;
        this.email = email;
        this.designation = designation;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return id == staff.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
