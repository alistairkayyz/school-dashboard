package alistair.dashboard.services;

import alistair.dashboard.models.Course;
import alistair.dashboard.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Optional<Course> getCourse(long id){
        return repository.findById(id);
    }

    public List<Course> getCourses(){
        return repository.findAll();
    }

    public boolean courseExists(long id){
        return repository.existsById(id);
    }

    public CourseRepository getRepository() {
        return repository;
    }

    public void saveCourse(Course course){
        if (courseExists(course.getId()))
            throw new IllegalArgumentException("Course already exists for this id");

        repository.save(course);
    }
    public void updateCourse(Course course){
        repository.save(course);
    }

    public void deleteCourse(long id){
        repository.deleteById(id);
    }
}
