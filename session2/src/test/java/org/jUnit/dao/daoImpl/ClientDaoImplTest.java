//package org.jUnit.dao.daoImpl;
//import org.jUnit.db.DBTestConnection;
//import org.jUnit.entities.Client;
//import org.jUnit.entities.Employee;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ClientDaoImplTest {
//
//    private static ClientDaoImpl clientDao;
//
//    private static EmployeeDaoImpl employeeDao;
//
//    private static Client client;
//    private static Employee employee;
//
//
//    @BeforeAll
//    public static void setUp() {
//        Connection testConnection = DBTestConnection.establishTestConnection();
//
//        clientDao = new ClientDaoImpl(testConnection);
//        employeeDao = new EmployeeDaoImpl(testConnection);
//
//        employee = new Employee(
//                "kandiro",
//                "mourad",
//                LocalDate.of(1990, 5, 15),
//                "06828733924",
//                "hay anass",
//                LocalDate.of(2023, 9, 21),
//                "kandiro@gmail.com"
//        );
//
//        employeeDao.create(employee);
//
//        client = new Client(
//                "kandiro",
//                "mourad",
//                LocalDate.of(1990, 5, 15),
//                "06828733924",
//                "hay anass",
//                employee
//        );
//
//        clientDao.create(client);
//
//    }
//
//
//    @Test
//    public void testfindbyId() {
//        Optional<Client> find = clientDao.findByID(client.getCode());
//        System.out.println("HERE");
//        assertTrue(find.isPresent());
//    }
//
//
//}
