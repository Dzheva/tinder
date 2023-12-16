package application.services;

import application.models.User;
import application.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    public void addUser(User user) {
        repository.addEntity(user);
    }

    public User getUserById(int id) {
        return repository.getEntity(User.class, id);
    }

    public List<User> getUsersToShow(int id) {
        return repository.getUsersToShow(id);
    }
}
