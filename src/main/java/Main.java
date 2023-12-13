import application.WebServer;
import application.repository.BaseRepositoryImpl;
import application.repository.ChatRepository;
import application.models.Chat;
import application.models.Choice;
import application.models.User;
import application.services.ChatService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        initializeDatabase();
        initializeServer();
    }

    public static void initializeServer() {
        new WebServer("localhost", 5000).start();
    }

    public static void initializeDatabase() {
        BaseRepositoryImpl baseRepositoryImpl = new BaseRepositoryImpl();

        User firstUser = new User("test2", "", "Test 1");
        User secondUser  = new User("test2", "", "Test 2");
        User thirdUser  = new User("test3", "", "Test 3");
        User fourthUser  = new User("test4", "", "Test 4");
        baseRepositoryImpl.addEntity(firstUser);
        baseRepositoryImpl.addEntity(secondUser);
        baseRepositoryImpl.addEntity(thirdUser);

        Choice choice = new Choice(firstUser, secondUser, "like");
        baseRepositoryImpl.addEntity(choice);

        Chat chat = new Chat(List.of(firstUser, thirdUser));
        baseRepositoryImpl.addEntity(chat);
        User fetchedUser = baseRepositoryImpl.getEntity(User.class, 3);
        System.out.println(fetchedUser.chats.get(0).participants);

        ChatRepository chatRepository = new ChatRepository();
        chatRepository.createTestChat(1, 2);

        ChatService chatService = new ChatService();
        Chat chatBetweenUsers = chatService.getChatBetweenUsers(1, 2);
        System.out.println(chatBetweenUsers);
    }
}