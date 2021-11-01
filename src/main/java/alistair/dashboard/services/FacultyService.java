package alistair.dashboard.services;

import alistair.dashboard.models.Faculty;
import alistair.dashboard.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Optional<Faculty> getFaculty(long id){
        return repository.findById(id);
    }

    public List<Faculty> getFaculties(){
        return repository.findAll();
    }

    public boolean facultyExists(long id){
        return repository.existsById(id);
    }

    public void saveFaculty(Faculty faculty){
        if (facultyExists(faculty.getId()))
            throw new IllegalArgumentException("Faculty already exists for this id");

        repository.save(faculty);
    }

    public void updateFaculty(Faculty faculty){
        repository.save(faculty);
    }

    public void deleteFaculty(long id){
        repository.deleteById(id);
    }
}
