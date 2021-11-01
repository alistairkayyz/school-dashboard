package alistair.dashboard.repositories;

import alistair.dashboard.models.FirstYearMarks;
import alistair.dashboard.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface FirstYearRepository extends JpaRepository<FirstYearMarks,Long> {
    @Query("SELECT m FROM FirstYearMarks m WHERE m.student.id = ?1")
    List<FirstYearMarks> getMarksByStudentNumber(long studentNumber);

    @Query("SELECT m FROM FirstYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    List<FirstYearMarks> getMarksByYear(Date year1, Date year2);

    @Query("SELECT MAX(m.id) from FirstYearMarks m")
    String getLastID();

    @Query("SELECT m FROM FirstYearMarks m WHERE m.final_mark < ?1 AND m.year = ?2 AND m.status IS NOT NULL")
    List<FirstYearMarks> getFailedStudents(int finalMark, Date date);

    @Query("SELECT m FROM FirstYearMarks m WHERE m.status is null ")
    List<FirstYearMarks> getMarksByStatus();

    @Query("SELECT f FROM FirstYearMarks f GROUP BY f.student.id, f.year")
    List<FirstYearMarks> getStudentAndAcademicYear();

    @Query("SELECT f FROM FirstYearMarks f where f.student.id = ?1 AND f.year = ?2")
    List<FirstYearMarks> getByStudentNumberAndYear(long student_number, Date date);

    @Query("SELECT f FROM FirstYearMarks f GROUP BY f.year")
    List<FirstYearMarks> getAcademicYears();

    @Query("SELECT AVG(m.final_mark) FROM FirstYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    double getAvgMarksByYear(Date year1, Date year2);

    @Query("SELECT AVG(m.final_mark) FROM FirstYearMarks m")
    double getAvgSemesterMark();

    @Query("SELECT AVG(m.ca1) FROM FirstYearMarks m")
    double getAvgCa1Mark();

    @Query("SELECT AVG(m.ca2) FROM FirstYearMarks m")
    double getAvgCa2Mark();

    @Query("SELECT AVG(m.assignment) FROM FirstYearMarks m")
    double getAvgAssignmentMark();

    @Query("SELECT AVG(m.exam) FROM FirstYearMarks m")
    double getAvgExamMark();

    @Query("SELECT m.student FROM FirstYearMarks m WHERE m.year = ?1 group by m.student")
    List<Student> getStudentsByYear(Date date);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM FirstYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.final_mark_available = true")
    double getStudentAvgFinalMark(Date date, long student_number);

    @Query("SELECT m FROM FirstYearMarks m " +
            "WHERE m.final_mark < 50 " +
            "AND m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.status IS NOT NULL GROUP BY m.student.id")
    Optional<FirstYearMarks> getFailedSubject(Date date, long student_number);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM FirstYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.subject.id = ?2 " +
            "OR m.subject.id = ?3")
    double getTeacherAvg(Date date, long subject1, long subject2);

    @Query("SELECT f FROM FirstYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 1")
    List<FirstYearMarks> getMarksForSemOne(long student_number, Date date);

    @Query("SELECT f FROM FirstYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 2")
    List<FirstYearMarks> getMarksForSemTwo(long student_number, Date date);

    @Query("SELECT count(f.id) FROM FirstYearMarks f where f.subject.semester = 1 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemOne();

    @Query("SELECT count(f.id) FROM FirstYearMarks f where f.subject.semester = 2 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemTwo();
}
