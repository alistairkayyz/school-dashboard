package alistair.dashboard.models;

import java.sql.Date;

public class DistinctStudentYear {
    private long student_number;
    private Date date;

    public DistinctStudentYear() {
    }

    public long getStudent_number() {
        return student_number;
    }

    public void setStudent_number(long student_number) {
        this.student_number = student_number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
