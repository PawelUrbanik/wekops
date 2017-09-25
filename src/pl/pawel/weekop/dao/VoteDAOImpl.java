package pl.pawel.weekop.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.pawel.weekop.model.Vote;
import pl.pawel.weekop.model.VoteType;
import pl.pawel.weekop.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDAOImpl implements VoteDAO {
    //Zapytania do bazy
    private static final String CREATE_VOTE="INSERT INTO vote(discovery_id, user_id, date, type) VALUES (:discovery_id, :user_id, :date, :type);";
    private static final String READ_VOTE="SELECT vote_id, discovery_id, user_id, date, type FROM vote WHERE vote_id= :vote_id;";
    private static final String READ_VOTE_BY_DISCOVERY_USE_ID="SELECT vote_id, discovery_id, user_id, date, type FROM vote " +
            "WHERE user_id= :user_id AND discovery_id= :discovery_id;";
    private static final String UPDATE_VOTE="UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id;";
    private static final String READ_ALL_VOTES = "SELECT vote_id, user.user_id, discovery.discovery_id, type, vote.date FROM vote" +
            " LEFT JOIN user ON vote.user_id=user.user_id LEFT JOIN discovery ON vote.discovery_id=discovery.discovery_id;";

    private NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Vote create(Vote vote) {
        Vote copyVote = new Vote(vote);
        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("discovery_id", copyVote.getDiscoveryId());
        mapParam.put("user_id", copyVote.getUserId());
        mapParam.put("date", copyVote.getDate());
        mapParam.put("type", copyVote.getVoteType().toString());
        SqlParameterSource parameterSource = new MapSqlParameterSource(mapParam);
        KeyHolder holder = new GeneratedKeyHolder();
        int rowsAffected = template.update(CREATE_VOTE, parameterSource, holder);
        if (rowsAffected>0)
        {
            copyVote.setId((Long)holder.getKey());
        }
        return copyVote;
    }

    @Override
    public Vote read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("vote_id", primaryKey);
        Vote vote = template.queryForObject(READ_VOTE, parameterSource, new VoteRowMapper());
        return vote;
    }

    @Override
    public boolean update(Vote vote) {
        boolean result= false;
        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("date", vote.getDate());
        mapParam.put("type", vote.getVoteType().toString());
        mapParam.put("vote_id", vote.getId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(mapParam);
        int update = template.update(UPDATE_VOTE, parameterSource);
        if (update>0)
        {
            result = true;
            System.out.println("Result:" + update);
        }
        return result;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        List<Vote> allVotes = template.query(READ_ALL_VOTES, new VoteRowMapper());
        //System.out.println("daoiml: "+ allVotes);
        return  allVotes;
    }


    @Override
    public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("discovery_id", discoveryId);
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        Vote vote =null;
        try {
            vote = template.queryForObject(READ_VOTE_BY_DISCOVERY_USE_ID, parameterSource, new VoteRowMapper());
            System.out.println(vote);
        }
        catch (EmptyResultDataAccessException e)
        {
            System.out.println(vote + "vote not found");
           // e.printStackTrace();
        }
        return vote;
    }

    private class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setDiscoveryId(resultSet.getLong("discovery_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
    }
}
