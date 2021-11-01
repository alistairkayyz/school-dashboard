package alistair.dashboard.services;

import alistair.dashboard.models.Log;
import alistair.dashboard.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    private final LogRepository repository;

    public LogService(LogRepository repository) {
        this.repository = repository;
    }

    public Optional<Log> getLog(long id){
        return repository.findById(id);
    }

    public List<Log> getLogs(){
        return repository.findAll();
    }

    public boolean logExists(long id){
        return repository.existsById(id);
    }

    public void saveLog(Log log){
        if (logExists(log.getId()))
            throw new IllegalArgumentException("Log already exists for this id");

        repository.save(log);
    }

    public void updateLog(Log log){
        repository.save(log);
    }
}
