package application.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class BaseRepositoryImpl implements IBaseRepository {
    private final SessionFactory sessionFactory;

    public BaseRepositoryImpl() {
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

    public <T> List<T> getAllEntities(Class<T> entityClass) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + entityClass.getName();
            Query<T> query = session.createQuery(hql, entityClass);
            return query.list();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
