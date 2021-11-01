package alistair.dashboard.services;

import alistair.dashboard.models.Staff;
import alistair.dashboard.repositories.StaffRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    public Optional<Staff> getStaff(long id){
        return repository.findById(id);
    }

    public List<Staff> getAllStaff(){
        return repository.findAll();
    }
    public String getLastID(){
        return repository.getLastID();
    }

    public void deleteStaff(long id){
        repository.deleteById(id);
    }
    public void saveStaff(Staff staff){
        if (staffExists(staff.getId()))
            throw new IllegalArgumentException("Staff already exists for this id.");
        repository.save(staff);
    }

    public boolean staffExists(long id){
        return repository.existsById(id);
    }

    public int getTotalTeachers(){
        return repository.getTotalTeachers();
    }

    public List<Long> getFirstYearTeachers(){
        return repository.getFirstYearTeachers();
    }

    public List<Long> getSecondYearTeachers(){
        return repository.getSecondYearTeachers();
    }

    public List<Long> getThirdYearTeachers(){
        return repository.getThirdYearTeachers();
    }
}
