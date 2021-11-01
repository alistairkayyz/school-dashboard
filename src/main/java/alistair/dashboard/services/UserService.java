package alistair.dashboard.services;

import alistair.dashboard.models.User;
import alistair.dashboard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(User user){
        return userRepository.existsById(user.getId());
    }


    public Optional<User> getUser(long id){
        return userRepository.findById(id);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        if (userExists(user))
            throw new IllegalArgumentException("User already exists for this username.");
        userRepository.save(user);
    }

    public void updatePassword(User user){
        userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

}
