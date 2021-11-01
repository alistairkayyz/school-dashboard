package alistair.dashboard.services;

import alistair.dashboard.models.Message;
import alistair.dashboard.models.Student;
import alistair.dashboard.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Optional<Message> getMessage(long id){
        return repository.findById(id);
    }

    public List<Message> getMessages(){
        return repository.findAll();
    }

    public void saveMessage(Message message){
        repository.save(message);
    }

    public void saveMessage(Message message, Student student){
        message.setStudents(student);
        repository.save(message);
    }

    public void saveMessage(Message message, List<Student> students){
        message.setStudents(students);
        repository.save(message);
    }

    public boolean messageExists(long id){
        return repository.existsById(id);
    }

    public void deleteMessage(long id){
        repository.deleteById(id);
    }
}
