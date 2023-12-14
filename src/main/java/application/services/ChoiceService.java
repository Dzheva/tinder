package application.services;

import application.models.Choice;
import application.repositories.ChoiceRepository;

import java.util.List;

public class ChoiceService {
    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    public void addChoice(Choice choice){
        choiceRepository.addEntity(choice);
    }

    public List<Choice> getUserChoices(int userId){
        return choiceRepository.getUserChoices(userId);
    }

}
