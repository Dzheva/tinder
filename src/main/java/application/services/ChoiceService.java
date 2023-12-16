package application.services;

import application.models.Choice;
import application.repositories.ChoiceRepository;

import java.util.List;

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
}
