package alistair.dashboard.repositories;

import alistair.dashboard.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.id BETWEEN ?1 AND ?2")
    List<Student> getByRange(long from, long to);

    @Query("SELECT count(s.id) " +
            "FROM Student s " +
            "WHERE s.id IN (SELECT f.student.id FROM FirstYearMarks f) " +
            "or s.id IN (SELECT m.student.id FROM SecondYearMarks m) " +
            "or s.id IN (SELECT t.student.id FROM ThirdYearMarks t)")
    int getTotalStudents();
}
