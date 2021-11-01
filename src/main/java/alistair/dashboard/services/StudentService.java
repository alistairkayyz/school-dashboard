package alistair.dashboard.services;

import alistair.dashboard.models.Student;
import alistair.dashboard.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public Optional<Student> getStudent(long id){
        return repository.findById(id);
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public List<Student> getByRange(long from, long to){
        return repository.getByRange(from, to);
    }

    public boolean studentExists(long id){
        return repository.existsById(id);
    }

    public void saveStudent(Student student){
        if (studentExists(student.getId()))
            throw new IllegalArgumentException("Student exists for this Student Number");

        repository.save(student);
    }
    public void saveAllStudents(List<Student> students){

        for (Student student : students) {
            if (studentExists(student.getId()))
                throw new IllegalArgumentException("Student exists for this Student Number");
        }

        repository.saveAll(students);
    }

    public void updateStudent(Student student){
        repository.save(student);
    }

    public void deleteStudent(long id){
        repository.deleteById(id);
    }

    public int getTotalStudents(){
        return repository.getTotalStudents();
    }
}
