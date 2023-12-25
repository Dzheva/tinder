import application.WebServer;
import application.models.Chat;
import application.models.Message;
import application.models.User;
import application.repositories.Repository;
import application.services.ChatService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        initializeDatabase();
        initializeServer();
    }

    public static void initializeServer() {
        new WebServer("localhost", 5000).start();
    }

    public static void initializeDatabase() {
        Repository repository = new Repository();

        User firstUser = new User("test1", "password", "Test 1");
        User secondUser = new User("test2", "password", "Test 2");
        User thirdUser = new User("test3", "password", "Test 3");
        User fourthUser = new User("test4", "password", "Test 4");
        User fifthUser = new User("test5", "password", "Test 5");
        repository.addEntities(List.of(firstUser, secondUser, thirdUser, fourthUser, fifthUser));
        repository.addEntity(new Chat(List.of(firstUser, thirdUser)));

        User fetchedUser = repository.getEntity(User.class, 1);
        logger.info(String.valueOf(fetchedUser));

        Chat chat = new Chat(List.of(firstUser, secondUser));
        chat.messages = new ArrayList<>(List.of(
                new Message(chat, firstUser, 1702505817, "Hello " + secondUser.fullName),
                new Message(chat, secondUser, 1702565817, "Hi " + firstUser.fullName),
                new Message(chat, firstUser, 1702625817, "How are you?"),
                new Message(chat, secondUser, 1702685817, "I am good, thanks!")));

        chat.messages.add(new Message(chat, firstUser, 1702505817, "Test from Ihor " + secondUser.fullName));
        repository.addEntity(chat);

        ChatService chatService = new ChatService();
        Chat chatBetweenUsers = chatService.getChatBetweenUsers(1, 2);
        logger.info(String.valueOf(chatBetweenUsers));
    }
}
