package application.repositories;

import org.hibernate.SessionFactory;

import java.util.List;

public interface IRepository {
    void addEntity(Object entity);

    void addEntities(List<Object> entities);

    <T> T getEntity(Class<T> entityClass, int id);

    <T> List<T> getEntities(Class<T> entityClass);

    SessionFactory getSessionFactory();
}
