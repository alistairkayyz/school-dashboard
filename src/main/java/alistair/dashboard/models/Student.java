package alistair.dashboard.models;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table
public class Student implements Serializable {
    @Id
    @NotNull(message = "Student Number cannot be null")
    private long id;

    //@NotNull(message = "Title cannot be null")
    private String title;

    //@NotNull(message = "Firstname cannot be null")
    private String firstname;

    //@NotNull(message = "Surname cannot be null")
    private String surname;

    //@NotNull(message = "Date of Birth cannot be null")
    private Date date_of_birth;

    //@NotNull(message = "Language cannot be null")
    private String home_language;

    //@NotNull(message = "ID Number cannot be null")
    private String id_number;

    //@NotNull(message = "Cellphone cannot be null")
    private String cellphone;

    //@NotNull(message = "Email cannot be null")
    @Email(message = "Invalid Email")
    private String email;

    //@NotNull(message = "Gender cannot be null")
    private String gender;

    //@NotNull(message = "Nationality cannot be null")
    private String nationality;

    //@NotNull(message = "Street Address cannot be null")
    private String street_address;

    //@NotNull(message = "Suburb cannot be null")
    private String suburb;

    //@NotNull(message = "City cannot be null")
    private String city;

    //@NotNull(message = "Post Code cannot be null")
    private String post_code;

    private Date reg_date;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Parent parent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;


    public Student() {
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

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

    public String getHome_language() {
        return home_language;
    }

    public void setHome_language(String homeLanguage) {
        this.home_language = homeLanguage;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String idNumber) {
        this.id_number = idNumber;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String streetAddress) {
        this.street_address = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String postCode) {
        this.post_code = postCode;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date regDate) {
        this.reg_date = regDate;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + date_of_birth +
                ", homeLanguage='" + home_language + '\'' +
                ", idNumber='" + id_number + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", streetAddress='" + street_address + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + post_code + '\'' +
                ", regDate=" + reg_date +
                '}';
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
