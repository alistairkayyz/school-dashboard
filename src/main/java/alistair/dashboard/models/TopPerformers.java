package alistair.dashboard.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TopPerformers {
    private long student_number;
    private String name;
    private String gender;
    private String email;
    private String course_name;
    private int year;
    private double aggregate;

    public TopPerformers(long student_number, String name, String gender, String email, String course_name, int year, double aggregate) {
        this.student_number = student_number;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.course_name = course_name;
        this.year = year;
        this.aggregate = aggregate;
    }

    public long getStudent_number() {
        return student_number;
    }

    public void setStudent_number(long student_number) {
        this.student_number = student_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAggregate() {
        return aggregate;
    }

    public String getFormattedAggregate(){
        return getAggregate() + "%";
    }

    public void setAggregate(double aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public String toString() {
        return "TopPerformers{" +
                "student_number=" + student_number +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", course_name='" + course_name + '\'' +
                ", year=" + year +
                ", aggregate=" + aggregate +
                '}';
    }
}
