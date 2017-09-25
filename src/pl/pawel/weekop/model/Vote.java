package pl.pawel.weekop.model;

import java.sql.Timestamp;

public class Vote implements Comparable<Vote> {
    private long id;
    private long discoveryId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(Vote vote)
    {
        this.setId(vote.getId());
        this.setDiscoveryId(vote.getDiscoveryId());
        this.setUserId(vote.getUserId());
        this.setDate(vote.getDate());
        this.setVoteType(vote.getVoteType());
    }

    public Vote(long id, long discoveryId, long userId, Timestamp date, VoteType voteType) {
        this.id = id;
        this.discoveryId = discoveryId;
        this.userId = userId;
        this.date = date;
        this.voteType = voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDiscoveryId() {
        return discoveryId;
    }

    public void setDiscoveryId(long discoveryId) {
        this.discoveryId = discoveryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (discoveryId != vote.discoveryId) return false;
        if (userId != vote.userId) return false;
        if (!date.equals(vote.date)) return false;
        return voteType == vote.voteType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (discoveryId ^ (discoveryId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + voteType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", discoveryId=" + discoveryId +
                ", userId=" + userId +
                ", date=" + date +
                ", voteType=" + voteType +
                '}';
    }

    @Override
    public int compareTo(Vote o) {
        long idCompare = ((Vote) o).getId();
        return (int) (this.getId() - idCompare);
    }
}
