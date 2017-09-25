package pl.pawel.weekop.dao;

import pl.pawel.weekop.model.User;
import pl.pawel.weekop.model.Vote;

import java.util.List;

public interface VoteDAO extends GenericDAO<Vote, Long> {
    List<Vote> getAll();
    public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
}
