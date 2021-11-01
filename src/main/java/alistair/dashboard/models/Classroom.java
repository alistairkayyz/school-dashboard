package alistair.dashboard.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "class")
public class Classroom implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Year cannot be null")
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @NotNull(message = "Course cannot be null")
    private Course course;

    public Classroom() {
    }

    public Classroom(long id, String name, int year, Course course) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classroom that = (Classroom) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "CourseClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", course=" + course +
                '}';
    }
}
