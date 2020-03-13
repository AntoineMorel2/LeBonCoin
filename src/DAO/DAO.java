package DAO;

import java.util.Collection;

public interface DAO<T> {
    public boolean create(T obj);
    public T fetch(int id);
    public Collection<T> fetchAll();
    public boolean update(T obj);
    public boolean delete(T obj);
    public void close(); // Connection closure of the data source
}
