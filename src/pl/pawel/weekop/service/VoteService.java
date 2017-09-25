package pl.pawel.weekop.service;

import pl.pawel.weekop.dao.DAOFactory;
import pl.pawel.weekop.dao.VoteDAO;
import pl.pawel.weekop.model.Vote;
import pl.pawel.weekop.model.VoteType;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class VoteService {
    public Vote addVote(long discovery_id, long user_id, VoteType voteType)
    {
        Vote vote = new Vote();
        vote.setDiscoveryId(discovery_id);
        vote.setUserId(user_id);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        vote = voteDAO.create(vote);
        return vote;
    }
    public Vote updateVote(long discovery_id, long user_id, VoteType voteType)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        Vote voteToUpdate = voteDAO.getVoteByUserIdDiscoveryId(user_id,discovery_id);
        if (voteToUpdate != null && !voteToUpdate.getVoteType().equals(voteType))
        {
            //System.out.println("Nie jest null i nie jest taki sam rodzaj glosu");
            voteToUpdate.setVoteType(voteType);
            voteToUpdate.setDate(new Timestamp(new Date().getTime()));
            voteDAO.update(voteToUpdate);
        }
        else
        {
            System.out.println("Null lub taki sam rodzaj glosu");
        }
        return voteToUpdate;
    }

    public Vote addOrUpdateVote(long discovery_id, long user_id, VoteType voteType)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdDiscoveryId(user_id,discovery_id);
        Vote resultVote = null;
        if (vote == null)
        {
            resultVote= addVote(discovery_id,user_id,voteType);
        }
        else
        {
            resultVote = updateVote(discovery_id,user_id,voteType);
        }
        return resultVote;
    }

    public Vote getVoteByDiscoveryUserId(long discoveryId, long userId)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO  voteDAO = factory.getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId,discoveryId);
        return vote;
    }

    public List<Vote> getAllVotes(){
        System.out.println("null comparator");
        return getAllVotes(null);}
    public List<Vote> getAllVotes(Comparator<Vote> comparator)
    {
    DAOFactory factory = DAOFactory.getDAOFactory();
    VoteDAO voteDAO = factory.getVoteDAO();
    List<Vote> votes =voteDAO.getAll();
       // System.out.println("voteService: "+ votes);
    if (comparator != null && votes != null)
    {
        votes.sort(comparator);
    }
    return votes;
    }

}
