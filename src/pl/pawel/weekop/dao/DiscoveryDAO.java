package pl.pawel.weekop.dao;

import pl.pawel.weekop.model.Discovery;

import java.util.List;

public interface DiscoveryDAO extends GenericDAO <Discovery, Long> {
List<Discovery> getAll();
}
