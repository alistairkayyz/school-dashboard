package alistair.dashboard.repositories;

import alistair.dashboard.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("SELECT s.id FROM Subject s where s.staff.id = ?1")
    List<Integer> getSubjectId(long staff_id);
}
