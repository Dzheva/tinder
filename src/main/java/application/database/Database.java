package application.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Database {
    private final SessionFactory sessionFactory;

    public Database() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addEntity(Object entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    public <T> T getEntity(Class<T> entityClass, int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }
}
