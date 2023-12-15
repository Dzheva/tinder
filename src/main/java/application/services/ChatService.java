package application.services;

import application.repositories.ChatRepository;
import application.models.Chat;

public class ChatService {
    private final ChatRepository repository;

    public ChatService() {
        this.repository = new ChatRepository();
    }

    public Chat getChatBetweenUsers(int senderId, int recipientId) {
        return repository.getChatBetweenUsers(senderId, recipientId);
    }
}
