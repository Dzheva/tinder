package application.services;

import application.models.Choice;
import application.repositories.ChoiceRepository;

public class ChoiceService {
    private final ChoiceRepository repository;

    public ChoiceService() {
        this.repository = new ChoiceRepository();
    }

    public void addChoice(Choice choice) {
        Choice existingChoice = repository.getChoice(choice.initiator.id, choice.target.id);
        if (existingChoice != null) {
            repository.updateChoiceValue(choice.initiator.id, choice.target.id, choice.value);
        } else {
            repository.addEntity(choice);
        }
    }
}
