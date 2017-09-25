package pl.pawel.weekop.dao;

import pl.pawel.weekop.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {
    List<User> getAll();
    User getsUserByUsername(String username );
}
