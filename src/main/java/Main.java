import application.WebServer;
import application.database.Database;
import application.database.models.Chat;
import application.database.models.Choice;
import application.database.models.User;

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
        Database database = new Database();

        User firstUser = new User("test2", "", "Test 1");
        User secondUser  = new User("test2", "", "Test 2");
        User thirdUser  = new User("test3", "", "Test 3");
        database.addEntity(firstUser);
        database.addEntity(secondUser);
        database.addEntity(thirdUser);

        Choice choice = new Choice(firstUser, secondUser, "like");
        database.addEntity(choice);

        Chat chat = new Chat(List.of(firstUser, thirdUser));
        database.addEntity(chat);
        User fetchedUser = database.getEntity(User.class, 3);
        System.out.println(fetchedUser.chats.get(0).participants);
    }
}