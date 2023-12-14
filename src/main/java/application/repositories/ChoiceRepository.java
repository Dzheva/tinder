package application.repositories;

import application.models.Choice;
import application.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ChoiceRepository extends Repository{
    public List<Choice> getUserChoices(int userId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "FROM Choice WHERE initiator.id = :userId";
            Query<Choice> query = session.createQuery(hql, Choice.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

}
