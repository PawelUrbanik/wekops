package pl.pawel.weekop.service;

import pl.pawel.weekop.dao.DAOFactory;
import pl.pawel.weekop.dao.DiscoveryDAO;
import pl.pawel.weekop.model.Discovery;
import pl.pawel.weekop.model.User;

import java.util.Comparator;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class DiscoveryService {

    public void addDiscovery(String name, String desc, String url, User user)
    {
    Discovery discovery = createDiscoveryObjec(name,desc,url,user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
        discoveryDAO.create(discovery);
    }

    private Discovery createDiscoveryObjec(String name, String desc, String url, User user)
    {
        Discovery discovery = new Discovery();
        discovery.setName(name);
        discovery.setDescription(desc);
        discovery.setUrl(url);
        User userCopy = new User(user);
        discovery.setUser(userCopy);
        discovery.setTimestamp(new Timestamp(new Date().getTime()));
        return discovery;
    }

    public List<Discovery> getAllDiscoveries()
    {
        return getAllDiscoveries(null);
    }
    public List<Discovery> getAllDiscoveries(Comparator<Discovery> comparator)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
        List<Discovery> discoveries = discoveryDAO.getAll();
        if (comparator != null && discoveries != null)
        {
            discoveries.sort(comparator);
        }
        return discoveries;
    }

    public boolean updateDiscovery(Discovery discovery)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
        boolean result = discoveryDAO.update(discovery);
        return result;
    }

    public Discovery getDiscoveryById(long id)
    {
        DAOFactory factory = DAOFactory.getDAOFactory();
        DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
        Discovery discovery = discoveryDAO.read(id);
        return discovery;
    }
}
