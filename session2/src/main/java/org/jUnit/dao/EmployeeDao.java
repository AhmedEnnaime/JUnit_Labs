package org.jUnit.dao;

import org.jUnit.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    public Optional<Employee> create(Employee employee);

    public Optional<Employee> findByID(Integer id);

    public List<Employee> getAll();

    public Optional<Employee> update(Integer id, Employee employee);

    public boolean delete(Integer id);

    public boolean deleteAll();
}
