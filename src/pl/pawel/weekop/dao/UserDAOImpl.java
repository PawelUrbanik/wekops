package pl.pawel.weekop.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.model.User;
import pl.pawel.weekop.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    //Zapytania do bazy
    private static final String CREATE_USER = "INSERT INTO  user(username, email, password, is_active) VALUES(:username, :email, :password, :active);";
    private static final String USER_ROLE_QUERY ="INSERT INTO user_role(username) VALUES(:username);";
    private static final String READ_USER_BY_ID = "SELECT user_id, username, email, password,is_active FROM user WHERE user_id= :id;";
    private static final String READ_USER_BY_USERNAME= "SELECT user_id, username, email, password, is_active FROM user WHERE username = :username ;";
    private static final String READ_ALL_USERS = "SELECT user_id, username, email, is_active, password FROM user;";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        this.template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    //Dodawanie nowego usera
    public User create(User user)
    {
        User resultUser = new User(user);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        int updateRows = template.update(CREATE_USER, parameterSource,holder);
        if (updateRows >0)
        {
            resultUser.setId((Long)holder.getKey());
            setPrivigiles(resultUser);
            System.out.println("Dodano użytkownika o id: " + holder.getKey());
    }
        System.out.println(updateRows);
        return resultUser;
    }

    // Nadanie domyślnych praw
    private void setPrivigiles(User user)
    {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        int rows = template.update(USER_ROLE_QUERY, parameterSource);
        System.out.println("Role: " + rows);
    }
    @Override
    public User read(Long primaryKey)
    {
        User resultUser = null;
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(READ_USER_BY_ID, parameterSource, new UserRowMapper());
        return resultUser;
    }
    @Override
    public User getsUserByUsername(String username) {
       User resultUser = new User();
       SqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
        resultUser = template.queryForObject(READ_USER_BY_USERNAME, parameterSource, new UserRowMapper());
        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<User> getAll()
    {
        List<User> allUsers = template.query(READ_ALL_USERS, new UserRowMapper());
        return  allUsers;
    }


    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user  = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }
}
