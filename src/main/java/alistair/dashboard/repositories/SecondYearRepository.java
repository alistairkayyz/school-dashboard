package alistair.dashboard.repositories;

import alistair.dashboard.models.SecondYearMarks;
import alistair.dashboard.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SecondYearRepository extends JpaRepository<SecondYearMarks,Long> {
    @Query("SELECT m FROM SecondYearMarks m WHERE m.student.id = ?1")
    List<SecondYearMarks> getMarksByStudentNumber(long studentNumber);

    @Query("SELECT m FROM SecondYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    List<SecondYearMarks> getMarksByYear(Date year1, Date year2);

    @Query("SELECT MAX(m.id) from SecondYearMarks m")
    String getLastID();

    @Query("SELECT m FROM SecondYearMarks m WHERE m.final_mark < ?1 AND m.year = ?2 AND m.status IS NOT NULL")
    List<SecondYearMarks> getFailedStudents(int finalMark, Date date);

    @Query("SELECT m FROM SecondYearMarks m WHERE m.status is null ")
    List<SecondYearMarks> getMarksByStatus();

    @Query("SELECT s FROM SecondYearMarks s GROUP BY s.student.id, s.year")
    List<SecondYearMarks> getStudentAndAcademicYear();

    @Query("SELECT s FROM SecondYearMarks s where s.student.id = ?1 AND s.year = ?2")
    List<SecondYearMarks> getByStudentNumberAndYear(long student_number, Date date);

    @Query("SELECT f FROM SecondYearMarks f GROUP BY f.year")
    List<SecondYearMarks> getAcademicYears();

    @Query("SELECT AVG(m.final_mark) FROM SecondYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    double getAvgMarksByYear(Date year1, Date year2);

    @Query("SELECT AVG(m.final_mark) FROM SecondYearMarks m")
    double getAvgSemesterMark();

    @Query("SELECT AVG(m.ca1) FROM SecondYearMarks m")
    double getAvgCa1Mark();

    @Query("SELECT AVG(m.ca2) FROM SecondYearMarks m")
    double getAvgCa2Mark();

    @Query("SELECT AVG(m.assignment) FROM SecondYearMarks m")
    double getAvgAssignmentMark();

    @Query("SELECT AVG(m.exam) FROM SecondYearMarks m")
    double getAvgExamMark();

    @Query("SELECT m.student FROM SecondYearMarks m WHERE m.year = ?1 group by m.student")
    List<Student> getStudentsByYear(Date date);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM SecondYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.final_mark_available = true")
    double getStudentAvgFinalMark(Date date, long student_number);

    @Query("SELECT m FROM SecondYearMarks m " +
            "WHERE m.final_mark < 50 " +
            "AND m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.status IS NOT NULL GROUP BY m.student.id")
    Optional<SecondYearMarks> getFailedSubject(Date date, long student_number);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM SecondYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.subject.id = ?2 " +
            "OR m.subject.id = ?3")
    double getTeacherAvg(Date date, long subject1, long subject2);

    @Query("SELECT f FROM SecondYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 1")
    List<SecondYearMarks> getMarksForSemOne(long student_number, Date date);

    @Query("SELECT f FROM SecondYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 2")
    List<SecondYearMarks> getMarksForSemTwo(long student_number, Date date);

    @Query("SELECT count(f.id) FROM SecondYearMarks f where f.subject.semester = 1 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemOne();

    @Query("SELECT count(f.id) FROM SecondYearMarks f where f.subject.semester = 2 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemTwo();
}
