package pl.pawel.weekop.model;

import java.sql.Timestamp;

public class Discovery implements Comparable<Discovery> {
    private long id;
    private String name;
    private String description;
    private String url;
    private Timestamp timestamp;
    private User user;
    private int upVote;
    private int downVote;

    public Discovery() {
    }

    public Discovery(Discovery discovery)
    {
        this.setId(discovery.getId());
        this.setName(discovery.getName());
        this.setDescription(discovery.getDescription());
        this.setUrl(discovery.getUrl());
        this.setTimestamp(discovery.getTimestamp());
        this.setUser(discovery.getUser());
        this.setUpVote(discovery.getUpVote());
        this.setDownVote(discovery.getDownVote());
    }

    public Discovery(long id, String name, String description, String url, Timestamp timestamp, User user, int upVote, int downVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.timestamp = timestamp;
        this.user = user;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discovery discovery = (Discovery) o;

        if (id != discovery.id) return false;
        if (upVote != discovery.upVote) return false;
        if (downVote != discovery.downVote) return false;
        if (!name.equals(discovery.name)) return false;
        if (description != null ? !description.equals(discovery.description) : discovery.description != null)
            return false;
        if (!url.equals(discovery.url)) return false;
        if (!timestamp.equals(discovery.timestamp)) return false;
        return user.equals(discovery.user);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + url.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + upVote;
        result = 31 * result + downVote;
        return result;
    }

    @Override
    public String toString() {
        return "Discovery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    @Override
    public int compareTo(Discovery o) {
        long compareId = ((Discovery)o).getId();
        return (int) (this.getId() - compareId);
    }
}
