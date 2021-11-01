package alistair.dashboard.repositories;

import alistair.dashboard.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT MAX(s.id) from Staff s")
    String getLastID();

    @Query("SELECT count(s.designation) " +
            "FROM Staff s " +
            "WHERE s.designation = 'Teacher' " +
            "AND s.id " +
            "IN (SELECT s.staff.id FROM Subject s)")
    int getTotalTeachers();

    @Query("SELECT s.id " +
            "FROM Staff s " +
            "WHERE s.id " +
            "IN (SELECT s.staff.id FROM Subject s where s.classroom.id = 1)")
    List<Long> getFirstYearTeachers();

    @Query("SELECT s.id " +
            "FROM Staff s " +
            "WHERE s.id " +
            "IN (SELECT s.staff.id FROM Subject s where s.classroom.id = 2)")
    List<Long> getSecondYearTeachers();

    @Query("SELECT s.id " +
            "FROM Staff s " +
            "WHERE s.id " +
            "IN (SELECT s.staff.id FROM Subject s where s.classroom.id = 3)")
    List<Long> getThirdYearTeachers();
}
