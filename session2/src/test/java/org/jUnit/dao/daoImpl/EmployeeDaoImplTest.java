package org.jUnit.dao.daoImpl;

import org.jUnit.db.DBTestConnection;
import org.jUnit.entities.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDaoImplTest {

    private static EmployeeDaoImpl employeeDao;

    private static Employee employee;

    @BeforeAll
    public static void setUp() {
        Connection testConnection = DBTestConnection.establishTestConnection();

        employeeDao = new EmployeeDaoImpl(testConnection);

            employee = new Employee(
                    "kandiro",
                    "mourad",
                    LocalDate.of(1990, 5, 15),
                    "06828733924",
                    "hay anass",
                    LocalDate.of(2023, 9, 21),
                    "kandiro@gmail.com"
            );

            employeeDao.create(employee);
    }

    @Test
    public void testCreate() {
        Employee employee = new Employee(
                "Abdelali",
                "Hotgame",
                LocalDate.of(1990, 5, 15),
                "068233924",
                "hay anass",
                LocalDate.of(2023, 9, 21),
                "hotgam@gmail.com"
        );

        Optional<Employee> createdEmployee = employeeDao.create(employee);
        assertTrue(createdEmployee.isPresent());

        assertEquals(employee.getFirstName(), createdEmployee.get().getFirstName());
        assertEquals(employee.getLastName(), createdEmployee.get().getLastName());
        assertEquals(employee.getBirthDate(), createdEmployee.get().getBirthDate());
        assertEquals(employee.getPhone(), createdEmployee.get().getPhone());
        assertEquals(employee.getAddress(), createdEmployee.get().getAddress());
        assertEquals(employee.getEmail(), createdEmployee.get().getEmail());
    }

    @Test
    public void testGetAll() {
        List<Employee> allEmployees = employeeDao.getAll();
        assertNotNull(allEmployees);
        assertFalse(allEmployees.isEmpty());
    }

    @Test
    public void testDelete() {
        Employee employee = new Employee(
                "Abdelali",
                "Hotgame",
                LocalDate.of(1990, 5, 15),
                "068233924",
                "hay anass",
                LocalDate.of(2023, 9, 21),
                "hotgam@gmail.com"
        );

        Optional<Employee> createdEmployee = employeeDao.create(employee);
        assertTrue(createdEmployee.isPresent());

        boolean isDeleted = employeeDao.delete(createdEmployee.get().getMatricule());

        assertTrue(isDeleted);
        Optional<Employee> deletedEmployee = employeeDao.findByID(createdEmployee.get().getMatricule());
        assertFalse(deletedEmployee.isPresent());
    }

    @Test
    public void testFinByID() {
        Optional<Employee> retrievedEmployee = employeeDao.findByID(employee.getMatricule());
        assertTrue(retrievedEmployee.isPresent());

        assertEquals("kandiro", retrievedEmployee.get().getLastName());
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee(
                "Aymen",
                "Servoy",
                LocalDate.of(2000, 1, 26),
                "06823347924",
                "sidi bouzid",
                LocalDate.of(2023, 9, 21),
                "servoy@gmail.com"
        );

        Optional<Employee> createdEmployee = employeeDao.create(employee);
        assertTrue(createdEmployee.isPresent());

        Employee updatedEmployee = new Employee(
                "UpdatedName",
                "UpdatedLastName",
                LocalDate.of(1995, 3, 10),
                "061234567890",
                "UpdatedAddress",
                LocalDate.of(2022, 8, 15),
                "updated.email@example.com"
        );

        Optional<Employee> updatedEmployeeOptional = employeeDao.update(createdEmployee.get().getMatricule(), updatedEmployee);
        assertTrue(updatedEmployeeOptional.isPresent());
    }

    @AfterAll
    public static void tearDown() {
        employeeDao.deleteAll();
    }
}
