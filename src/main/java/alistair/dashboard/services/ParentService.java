package alistair.dashboard.services;

import alistair.dashboard.models.Parent;
import alistair.dashboard.repositories.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {
    private final ParentRepository repository;

    public ParentService(ParentRepository repository) {
        this.repository = repository;
    }

    public Optional<Parent> getParent(long id){
        return repository.findById(id);
    }

    public List<Parent> getParents(){
        return repository.findAll();
    }

    public void saveParent(Parent parent){
        if (parentExists(parent.getId()))
            throw new IllegalArgumentException("Parent already exists for this id");

        repository.save(parent);
    }

    public void saveAllParent(List<Parent> parents){

        repository.saveAll(parents);
    }

    public void updateParent(Parent parent){
        repository.save(parent);
    }

    public boolean parentExists(long id){
        return repository.existsById(id);
    }

    public void deleteParent(long id){
        repository.deleteById(id);
    }
}
