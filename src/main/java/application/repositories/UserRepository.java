package application.repositories;

import application.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public final class UserRepository extends Repository {
    public List<User> getCarouselUsers(int userId) {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE id != :userId", User.class)
                    .setParameter("userId", userId).list();
        }
    }

    public User getUser(String username) {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username).getSingleResultOrNull();
        }
    }

    public User getUser(String username, String password) {
        try (Session session = getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE username = :username AND password = :password", User.class);
            query.setParameter("username", username).setParameter("password", password);
            return query.getSingleResultOrNull();
        }
    }
}
