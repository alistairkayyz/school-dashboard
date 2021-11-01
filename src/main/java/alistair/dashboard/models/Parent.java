package alistair.dashboard.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class Parent implements Serializable {
    @Id
    @NotNull(message = "ID cannot be null")
    private long id;

    @NotNull(message = "Firstname cannot be null")
    private String firstname;

    @NotNull(message = "Surname cannot be null")
    private String surname;

    @NotNull(message = "Relation cannot be null")
    private String relation;

    @NotNull(message = "Cellphone cannot be null")
    private String cellphone;

    @NotNull(message = "ID cannot be null")
    @Email(message = "Invalid Email")
    private String email;

    public Parent() {
    }

    public Parent(long id, String firstname, String surname, String relation, String cellphone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.relation = relation;
        this.cellphone = cellphone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        return id == parent.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", relation='" + relation + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
