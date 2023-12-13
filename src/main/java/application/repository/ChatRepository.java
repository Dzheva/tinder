package application.repository;

import application.models.Chat;
import application.models.Message;
import application.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository extends BaseRepositoryImpl {
    public Chat getChatBetweenUsers(int senderId, int recipientId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "SELECT c FROM Chat c " +
                    "JOIN FETCH c.messages m " +
                    "JOIN c.participants p " +
                    "WHERE SIZE(c.participants) = 2 AND :sender MEMBER OF c.participants AND :recipient MEMBER OF c.participants";

            Query<Chat> query = session.createQuery(hql, Chat.class);

            User sender = session.get(User.class, senderId);
            User recipient = session.get(User.class, recipientId);

            query.setParameter("sender", sender);
            query.setParameter("recipient", recipient);

            List<Chat> result = query.getResultList();

            if (!result.isEmpty()) {
                return result.get(0);
            }

            return null;
        }
    }

    public void createTestChat(int userId1, int userId2) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve users with ID 1 and 2
            User user1 = session.get(User.class, userId1);
            User user2 = session.get(User.class, userId2);

            // Create a new chat
            Chat chat = new Chat();
            chat.participants = new ArrayList<>();
            chat.participants.add(user1);
            chat.participants.add(user2);

            // Create and add messages to the chat
            Message message1 = new Message(chat, user1, System.currentTimeMillis(), "Hello User %d".formatted(userId1));
            Message message2 = new Message(chat, user2, System.currentTimeMillis() + 60000, "Hi User %d".formatted(userId2));
            Message message3 = new Message(chat, user1, System.currentTimeMillis() + 120000, "How are you?");
            Message message4 = new Message(chat, user2, System.currentTimeMillis() + 180000, "I am good, thanks!");

            chat.messages = new ArrayList<>();
            chat.messages.add(message1);
            chat.messages.add(message2);
            chat.messages.add(message3);
            chat.messages.add(message4);

            // Persist the chat and messages
            session.persist(chat);

            transaction.commit();
        }
    }
}
