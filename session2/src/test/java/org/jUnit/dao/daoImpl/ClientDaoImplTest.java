package org.jUnit.dao.daoImpl;
import org.jUnit.db.DBTestConnection;
import org.jUnit.entities.Client;
import org.jUnit.entities.Employee;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDaoImplTest {

    private static ClientDaoImpl clientDao;

    private EmployeeDaoImpl employeeDao;

    private Client client;
    private Employee employee;


    @BeforeEach
    public void setUp() {
        Connection testConnection = DBTestConnection.establishTestConnection();


        employeeDao = new EmployeeDaoImpl(testConnection);
        clientDao = new ClientDaoImpl(testConnection);

        employee = new Employee(
                "Abdelali",
                "Hotgame",
                LocalDate.of(1990, 5, 15),
                "068233924",
                "hay anass",
                LocalDate.of(2023, 9, 21),
                "hotgam@gmail.com"
        );

        employeeDao.create(employee);

        client = new Client(
                "kandiro",
                "mourad",
                LocalDate.of(1990, 5, 15),
                "06828733924",
                "hay anass",
                employee
        );

        clientDao.create(client);

    }


    @Test
    public void testFindById() {
        Optional<Client> retrievedClient = clientDao.findByID(client.getCode());
        assertTrue(retrievedClient.isPresent());
    }

    @Test
    public void testUpdate(){
        client.setLastName("makandiroch");
        Optional<Client> optionalclient = clientDao.update(client.getCode(), client);
        assertTrue(optionalclient.isPresent());
        assertEquals("makandiroch", optionalclient.get().getLastName());
//        optionalclient.ifPresent((clt)->{
//            assertEquals("makandiroch", clt.getLastName());
//        });
    }

    @Test
    public void testDelete(){
        Boolean deleted = clientDao.delete(client.getCode());
        assertTrue(deleted);
    }

    @AfterAll
    public static void testDeleteAll(){
        Boolean deleted = clientDao.deleteAll();
        assertTrue(deleted);
    }



}
