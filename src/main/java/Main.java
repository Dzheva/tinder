import application.WebServer;
import application.models.Chat;
import application.models.Message;
import application.models.User;
import application.repositories.Repository;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File(Path.of("src", "main", "resources", "application.db").toString());
        if (!file.exists()) initializeDatabase();
        initializeServer();
    }

    public static void initializeServer() {
        new WebServer("localhost", 5000).start();
    }

    public static void initializeDatabase() {
        Repository repository = new Repository();
        User firstUser = new User("tom", "password", "Tom Smith",
                "https://images.unsplash.com/photo-1474031317822-f51f48735ddd?w=735");
        User secondUser = new User("williams", "password", "Jane Williams",
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=735");
        User thirdUser = new User("clark", "password", "Brian Clark",
                "https://images.unsplash.com/photo-1564564321837-a57b7070ac4f?w=735");
        User fourthUser = new User("barbara", "password", "Barbara Cooper",
                "https://images.unsplash.com/photo-1593104547489-5cfb3839a3b5?w=735");
        User fifthUser = new User("richard", "password", "Richard Rodriguez",
                "https://images.unsplash.com/photo-1464802686167-b939a6910659?w=735");
        repository.addEntities(List.of(firstUser, secondUser, thirdUser, fourthUser, fifthUser));

        Chat chat = new Chat(List.of(firstUser, secondUser));
        chat.messages = new ArrayList<>(List.of(
                new Message(chat, firstUser, 1702505817, "Hello " + secondUser.fullName),
                new Message(chat, secondUser, 1702565817, "Hi " + firstUser.fullName),
                new Message(chat, firstUser, 1702625817, "How are you?"),
                new Message(chat, secondUser, 1702685817, "I am good, thanks!")));
        repository.addEntity(chat);
    }
}
