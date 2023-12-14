package application.repositories;

import application.models.Chat;
import application.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ChatRepository extends Repository {
    public Chat getChatBetweenUsers(int senderId, int recipientId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "SELECT c FROM Chat c " +
                    "JOIN FETCH c.messages m " +
                    "JOIN c.participants p " +
                    "WHERE :sender MEMBER OF c.participants AND :recipient MEMBER OF c.participants";

            Query<Chat> query = session.createQuery(hql, Chat.class);
            query.setParameter("sender", session.get(User.class, senderId));
            query.setParameter("recipient", session.get(User.class, recipientId));

            List<Chat> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }
}
