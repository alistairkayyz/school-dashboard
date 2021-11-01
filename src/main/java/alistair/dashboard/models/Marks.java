package alistair.dashboard.models;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@MappedSuperclass
public class Marks implements Serializable {
    @Id
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_number", referencedColumnName = "id")
    private Student student;

    private Date year;
    private int ca1;
    private boolean ca1submitted;
    private int ca2;
    private boolean ca2submitted;
    private int assignment;
    private boolean assignment_submitted;
    private int exam;
    private boolean exam_submitted;
    private int final_mark;
    private boolean final_mark_available;
    private String status;
    private String remarks;

    public Marks() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getCa1() {
        return ca1;
    }

    public void setCa1(int ca1) {
        this.ca1 = ca1;
    }

    public boolean isCa1submitted() {
        return ca1submitted;
    }

    public void setCa1submitted(boolean ca1submitted) {
        this.ca1submitted = ca1submitted;
    }

    public int getCa2() {
        return ca2;
    }

    public void setCa2(int ca2) {
        this.ca2 = ca2;
    }

    public boolean isCa2submitted() {
        return ca2submitted;
    }

    public void setCa2submitted(boolean ca2submitted) {
        this.ca2submitted = ca2submitted;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public boolean isAssignment_submitted() {
        return assignment_submitted;
    }

    public void setAssignment_submitted(boolean assignment_submitted) {
        this.assignment_submitted = assignment_submitted;
    }

    public int getExam() {
        return exam;
    }

    public void setExam(int exam) {
        this.exam = exam;
    }

    public boolean isExam_submitted() {
        return exam_submitted;
    }

    public void setExam_submitted(boolean exam_submitted) {
        this.exam_submitted = exam_submitted;
    }

    public int getFinal_mark() {
        return final_mark;
    }

    public void setFinal_mark(int final_mark) {
        this.final_mark = final_mark;
    }

    public boolean isFinal_mark_available() {
        return final_mark_available;
    }

    public void setFinal_mark_available(boolean final_mark_available) {
        this.final_mark_available = final_mark_available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marks that = (Marks) o;

        return id == that.id;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", year=" + year +
                ", ca1=" + ca1 +
                ", ca1submitted=" + ca1submitted +
                ", ca2=" + ca2 +
                ", ca2submitted=" + ca2submitted +
                ", assignment=" + assignment +
                ", assignment_submitted=" + assignment_submitted +
                ", exam=" + exam +
                ", exam_submitted=" + exam_submitted +
                ", final_mark=" + final_mark +
                ", final_mark_available=" + final_mark_available +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
