package alistair.dashboard.models;

import java.io.Serializable;

public class StudentMarks implements Serializable {
    private int ca1;
    private int ca2;
    private int assignment;
    private int exam;

    public StudentMarks() {
    }

    public int getCa1() {
        return ca1;
    }

    public void setCa1(int ca1) {
        this.ca1 = ca1;
    }

    public int getCa2() {
        return ca2;
    }

    public void setCa2(int ca2) {
        this.ca2 = ca2;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public int getExam() {
        return exam;
    }

    public void setExam(int exam) {
        this.exam = exam;
    }
}
