package application.services;

import application.models.User;
import application.repositories.UserRepository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.addEntity(user);
    }

    public User getUserById(int id){
        return userRepository.getEntity(User.class, id);
    }

    public List<User> getUsersToShow(int id){
        return userRepository.getUsersToShow(id);
    }

}
