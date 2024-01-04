package application.repositories;

import application.models.Chat;
import application.models.User;
import org.hibernate.Session;

public class ChatRepository extends Repository {
    public Chat getChat(User initiator, User target) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "SELECT chat FROM Chat chat " +
                    "WHERE :initiator MEMBER OF chat.participants AND :target MEMBER OF chat.participants";

            return session.createQuery(hql, Chat.class).setParameter("initiator", initiator)
                    .setParameter("target", target).getSingleResultOrNull();
        }
    }
}
