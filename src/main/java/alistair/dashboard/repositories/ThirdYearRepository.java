package alistair.dashboard.repositories;

import alistair.dashboard.models.SecondYearMarks;
import alistair.dashboard.models.Student;
import alistair.dashboard.models.ThirdYearMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ThirdYearRepository extends JpaRepository<ThirdYearMarks,Long> {
    @Query("SELECT m FROM ThirdYearMarks m WHERE m.student.id = ?1")
    List<ThirdYearMarks> getMarksByStudentNumber(long studentNumber);

    @Query("SELECT m FROM ThirdYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    List<ThirdYearMarks> getMarksByYear(Date year1, Date year2);

    @Query("SELECT MAX(m.id) from ThirdYearMarks m")
    String getLastID();

    @Query("SELECT m FROM ThirdYearMarks m WHERE m.final_mark < ?1 AND m.year = ?2 AND m.status IS NOT NULL")
    List<ThirdYearMarks> getFailedStudents(int finalMark, Date date);

    @Query("SELECT m FROM ThirdYearMarks m WHERE m.status is null ")
    List<ThirdYearMarks> getMarksByStatus();

    @Query("SELECT t FROM ThirdYearMarks t GROUP BY t.student.id, t.year")
    List<ThirdYearMarks> getStudentAndAcademicYear();

    @Query("SELECT t FROM ThirdYearMarks t where t.student.id = ?1 AND t.year = ?2")
    List<ThirdYearMarks> getByStudentNumberAndYear(long student_number, Date date);

    @Query("SELECT f FROM ThirdYearMarks f GROUP BY f.year")
    List<ThirdYearMarks> getAcademicYears();

    @Query("SELECT AVG(m.final_mark) FROM ThirdYearMarks m WHERE m.year = ?1 OR m.year = ?2")
    double getAvgMarksByYear(Date year1, Date year2);

    @Query("SELECT AVG(m.final_mark) FROM ThirdYearMarks m")
    double getAvgSemesterMark();

    @Query("SELECT AVG(m.ca1) FROM ThirdYearMarks m")
    double getAvgCa1Mark();

    @Query("SELECT AVG(m.ca2) FROM ThirdYearMarks m")
    double getAvgCa2Mark();

    @Query("SELECT AVG(m.assignment) FROM ThirdYearMarks m")
    double getAvgAssignmentMark();

    @Query("SELECT AVG(m.exam) FROM ThirdYearMarks m")
    double getAvgExamMark();

    @Query("SELECT m.student FROM ThirdYearMarks m WHERE m.year = ?1 group by m.student")
    List<Student> getStudentsByYear(Date date);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM ThirdYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.final_mark_available = true")
    double getStudentAvgFinalMark(Date date, long student_number);

    @Query("SELECT m FROM ThirdYearMarks m " +
            "WHERE m.final_mark < 50 " +
            "AND m.year = ?1 " +
            "AND m.student.id = ?2 " +
            "AND m.status IS NOT NULL GROUP BY m.student.id")
    Optional<ThirdYearMarks> getFailedSubject(Date date, long student_number);

    @Query("SELECT AVG(m.final_mark) " +
            "FROM ThirdYearMarks m " +
            "WHERE m.year = ?1 " +
            "AND m.subject.id = ?2 " +
            "OR m.subject.id = ?3")
    double getTeacherAvg(Date date, long subject1, long subject2);

    @Query("SELECT f FROM ThirdYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 1")
    List<ThirdYearMarks> getMarksForSemOne(long student_number, Date date);

    @Query("SELECT f FROM ThirdYearMarks f " +
            "where f.student.id = ?1 " +
            "AND f.year = ?2 " +
            "AND f.subject.semester = 2")
    List<ThirdYearMarks> getMarksForSemTwo(long student_number, Date date);

    @Query("SELECT count(f.id) FROM ThirdYearMarks f where f.subject.semester = 1 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemOne();

    @Query("SELECT count(f.id) FROM ThirdYearMarks f where f.subject.semester = 2 GROUP BY f.student.id, f.year")
    List<Long> getTotalStudentsForSemTwo();
}