package alistair.dashboard.services;

import alistair.dashboard.models.Classroom;
import alistair.dashboard.repositories.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    private final ClassroomRepository repository;

    public ClassroomService(ClassroomRepository repository) {
        this.repository = repository;
    }

    public Optional<Classroom> getClass(long id){
        return repository.findById(id);
    }

    public List<Classroom> getClasses(){
        return repository.findAll();
    }

    public boolean classExists(long id){
        return repository.existsById(id);
    }

    public void saveClass(Classroom classroom){
        if (classExists(classroom.getId()))
            throw new IllegalArgumentException("Class already exists for this id");

        repository.save(classroom);
    }

    public void updateClass(Classroom classroom){
        repository.save(classroom);
    }
    public void deleteClass(long id){
        repository.deleteById(id);
    }
}
