package application.repositories;

import application.models.Choice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ChoiceRepository extends Repository {
    public List<Choice> getUserChoices(int userId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "FROM Choice WHERE initiator.id = :userId AND value = 'like'";
            Query<Choice> query = session.createQuery(hql, Choice.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public Choice getChoiceByUsersId(int initiatorId, int targetId) {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "FROM Choice WHERE initiator.id = :initiatorId AND target.id = :targetId";
            Query<Choice> query = session.createQuery(hql, Choice.class);
            query.setParameter("initiatorId", initiatorId);
            query.setParameter("targetId", targetId);
            return query.uniqueResult();
        }
    }

    public void updateChoiceValue(int userId, int targetId, String newValue) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Choice SET value = :newValue WHERE initiator.id = :userId AND target.id = :targetId";
            Query query = session.createQuery(hql);
            query.setParameter("newValue", newValue);
            query.setParameter("userId", userId);
            query.setParameter("targetId", targetId);
            transaction.commit();
        }
    }
}
