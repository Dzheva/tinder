package application.services;

import application.models.Choice;
import application.models.User;
import application.repositories.ChoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceService {
    private final ChoiceRepository repository;

    public ChoiceService() {
        this.repository = new ChoiceRepository();
    }

    public void addChoice(Choice choice) {
        repository.addEntity(choice);
    }

    public List<Choice> getUserChoices(int userId) {
        return repository.getUserChoices(userId);
    }

    public List<User> getLikedUsers(int userId){
        return getUserChoices(userId).stream()
                .map(choice -> choice.target)
                .collect(Collectors.toList());
    }
}
