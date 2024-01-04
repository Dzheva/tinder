package application.services;

import application.models.Chat;
import application.models.Message;
import application.models.User;
import application.repositories.ChatRepository;

import java.util.List;

public class ChatService {
    private final ChatRepository repository;

    public ChatService() {
        this.repository = new ChatRepository();
    }

    public Chat getChat(User initiator, User target) {
        Chat chat = repository.getChat(initiator, target);
        if (chat == null) {
            chat = new Chat(List.of(initiator, target));
            repository.addEntity(chat);
        }
        return chat;
    }

    public void createMessage(Message message) {
        repository.addEntity(message);
    }
}
