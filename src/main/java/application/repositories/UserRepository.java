package application.repositories;

import application.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository extends Repository {
    public List<User> getUsersToShow(int userId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "FROM User WHERE id != :userId";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }
}
