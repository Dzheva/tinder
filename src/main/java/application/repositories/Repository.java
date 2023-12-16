package application.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Repository {
    private final SessionFactory sessionFactory;

    public Repository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addEntity(Object entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    public void addEntities(List<Object> entities) {
        entities.forEach(this::addEntity);
    }

    public <T> T getEntity(Class<T> entityClass, int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }

    public <T> List<T> getEntities(Class<T> entityClass) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("FROM " + entityClass.getName(), entityClass);
            return query.list();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
