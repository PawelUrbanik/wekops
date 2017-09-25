package pl.pawel.weekop.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable>{
    //CRUD
    T create(T newObejct);
    T read(PK primaryKey);
    boolean update(T updateObject);
    boolean delete(PK primaryKey);
    List<T> getAll();
}