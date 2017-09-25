package pl.pawel.weekop.dao;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscoveryDAOImpl implements DiscoveryDAO {
    private static final String CREATE_DISCOVERY="INSERT INTO discovery(user_id, name, description, url, date, up_vote, down_vote) VALUES" +
            " (:user_id, :name, :description, :url, :date, :up_vote, :down_vote);";
    private static final String READ_ALL_DISCOVERIES ="SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, " +
            "url, date, up_vote, down_vote FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id;" ;
    private static final String UPDATE_DISCOVERY = "UPDATE discovery SET name= :name, description= :description, url=:url ," +
            "user_id= :user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote  WHERE discovery_id=:discovery_id;";
    private static final String READ_DISCOVERY ="SELECT user.user_id, username, password, email, is_active, discovery_id, name, description, url, date, up_vote, down_vote " +
            "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id WHERE discovery_id=:discovery_id;" ;

    private NamedParameterJdbcTemplate template;

    public DiscoveryDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Discovery create(Discovery discovery) {
        Discovery resultDiscovery = new Discovery(discovery);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user_id", discovery.getUser().getId());
        paramMap.put("name", discovery.getName());
        paramMap.put("description", discovery.getDescription());
        paramMap.put("url", discovery.getUrl());
        paramMap.put("date", discovery.getTimestamp());
        paramMap.put("up_vote", discovery.getUpVote());
        paramMap.put("down_vote", discovery.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int rowsAffected = template.update(CREATE_DISCOVERY,parameterSource, keyHolder);
        if (rowsAffected >0)
        {
            resultDiscovery.setId((Long)keyHolder.getKey());
        }
        return resultDiscovery;
    }

    @Override
    public Discovery read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("discovery_id", primaryKey);
        Discovery discovery = template.queryForObject(READ_DISCOVERY, parameterSource, new DiscoveryRowMapper());
        return discovery;
    }

    @Override
    public boolean update(Discovery discovery) {

        boolean result= false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("discovery_id", discovery.getId());
        paramMap.put("user_id", discovery.getUser().getId());
        paramMap.put("name", discovery.getName());
        paramMap.put("description", discovery.getDescription());
        paramMap.put("url", discovery.getUrl());
        paramMap.put("date", discovery.getTimestamp());
        paramMap.put("up_vote", discovery.getUpVote());
        paramMap.put("down_vote", discovery.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int rowsAffected = template.update(UPDATE_DISCOVERY, parameterSource);
        if (rowsAffected > 0)
        {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<Discovery> getAll() {
        List<Discovery> discoveries = template.query(READ_ALL_DISCOVERIES, new DiscoveryRowMapper());
        return discoveries;
    }

    private class DiscoveryRowMapper implements RowMapper<Discovery> {
        @Override
        public Discovery mapRow(ResultSet resultSet, int i) throws SQLException {
            Discovery discovery = new Discovery();
            User user  = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setActive(true);
            discovery.setUser(user);

            discovery.setId(resultSet.getLong("discovery_id"));
            discovery.setName(resultSet.getString("name"));
            discovery.setUrl(resultSet.getString("url"));
            discovery.setDescription(resultSet.getString("description"));
            discovery.setUpVote(resultSet.getInt("up_vote"));
            discovery.setDownVote(resultSet.getInt("down_vote"));
            discovery.setTimestamp(resultSet.getTimestamp("date"));
            return discovery;
        }
    }
}
