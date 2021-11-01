package alistair.dashboard.services;

import alistair.dashboard.models.Subject;
import alistair.dashboard.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public Optional<Subject>  getSubject(long id){
        return repository.findById(id);
    }

    public List<Subject> getSubjects(){
        return repository.findAll();
    }

    public boolean subjectExists(long id){
        return repository.existsById(id);
    }

    public void saveSubject(Subject subject){
        if (subjectExists(subject.getId()))
            throw new IllegalArgumentException("Subject already exists for this id");

        repository.save(subject);
    }

    public List<Integer> getSubjectId(long staff_id){
        return repository.getSubjectId(staff_id);
    }

    public void updateSubject(Subject subject){
        repository.save(subject);
    }

    public void deleteSubject(long id){
        repository.deleteById(id);
    }
}
