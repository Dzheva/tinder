package application.services;

import application.repository.ChatRepository;
import application.models.Chat;

public class ChatService {
    private final ChatRepository repository;

    public ChatService() {
        this.repository = new ChatRepository();
    }

    public Chat getChatBetweenUsers(int sender, int recipient) {
        return repository.getChatBetweenUsers(sender, recipient);
    }
}
