package application.repository;

import org.hibernate.SessionFactory;

import java.util.List;

public interface IBaseRepository {
    void addEntity(Object entity);

    <T> T getEntity(Class<T> entityClass, int id);

    <T> List<T> getAllEntities(Class<T> entityClass);

    SessionFactory getSessionFactory();
}
