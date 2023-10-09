package org.jUnit.dao;

import org.jUnit.entities.Client;
import org.jUnit.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    public Optional<Client> create(Client client);

    public Optional<Client> findByID(Integer id);

    public List<Client> getAll();

    public Optional<Client> update(Integer id, Client client);

    public boolean delete(Integer id);

    public boolean deleteAll();
}
