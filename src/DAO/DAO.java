package DAO;

import java.util.Collection;

public interface DAO<T> {
    boolean create(T obj);
    T fetch(int id);
    Collection<T> fetchAll();
    Collection<T> fetchByName();
    boolean update(T obj);
    boolean delete(T obj);
    void close(); // Connection closure of the data source
}
