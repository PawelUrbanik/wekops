package pl.pawel.weekop.service;

import pl.pawel.weekop.dao.DAOFactory;
import pl.pawel.weekop.dao.UserDAO;
import pl.pawel.weekop.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

public class UserService {
    public void addUser(String username, String email, String password)
    {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String md5password = encryptPassword(password);
        user.setPassword(md5password);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }

    private String encryptPassword(String password)
    {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password =  new BigInteger(1,digest.digest()).toString(16);
        return md5Password;
    }

    public User getUserById(long userId)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.read(userId);
        return user;
    }

    public User getUserByUsername(String username)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        User user  = userDAO.getsUserByUsername(username);
        return user;
    }

    public List<User> getAllUsers(){return getAllUsers(null);}

    public List<User> getAllUsers(Comparator<User> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = userDAO.getAll();
        if (comparator != null && users != null)
        {
            users.sort(comparator);
        }
        return users;
    }
}
