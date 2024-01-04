package application.services;

import application.models.User;
import application.repositories.UserRepository;

import java.util.List;

public final class UserService {
    private final UserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    public User getUser(String username) {
        return repository.getUser(username);
    }

    public User getUser(String username, String password) {
        return repository.getUser(username, password);
    }

    public List<User> getCarouselUsers(int initiatorId) {
        return repository.getCarouselUsers(initiatorId);
    }
}
