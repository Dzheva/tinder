package application.repositories;

import application.models.Choice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class ChoiceRepository extends Repository {
    public Choice getChoice(int initiatorId, int targetId) {
        try (Session session = getSessionFactory().openSession()) {
            Query<Choice> query = session.createQuery(
                    "FROM Choice WHERE initiator.id = :initiatorId AND target.id = :targetId", Choice.class);
            query.setParameter("initiatorId", initiatorId).setParameter("targetId", targetId);
            return query.getSingleResultOrNull();
        }
    }

    public void updateChoiceValue(int initiatorId, int targetId, String value) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Choice SET value = :value WHERE initiator.id = :initiatorId AND target.id = :targetId";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("initiatorId", initiatorId)
                    .setParameter("targetId", targetId).setParameter("value", value).executeUpdate();
            transaction.commit();
        }
    }
}
