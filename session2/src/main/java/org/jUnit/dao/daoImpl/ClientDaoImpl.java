package org.jUnit.dao.daoImpl;

import org.jUnit.dao.ClientDao;
import org.jUnit.db.DBConnection;
import org.jUnit.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    private final Connection conn;

    public ClientDaoImpl() {
        conn = DBConnection.getInstance().establishConnection();
    }

    public ClientDaoImpl(Connection connection) {
        conn = connection;
    }

    @Override
    public Optional<Client> create(Client client) {
        String insertSQL = "INSERT INTO clients (firstName, lastName, birthDate, phone, address, employeeMatricule) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING code";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(client.getBirthDate()));
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.setString(5, client.getAddress());
            preparedStatement.setInt(6, client.getEmployee().getMatricule());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Creating client failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int code = generatedKeys.getInt(1);
                    client.setCode(code);
                } else {
                    throw new Exception("Creating client failed, no ID obtained.");
                }
            }

            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> findByID(Integer id) {
        String selectSQL = "SELECT * FROM clients WHERE code = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setCode(resultSet.getInt("code"));
                    client.setFirstName(resultSet.getString("firstName"));
                    client.setLastName(resultSet.getString("lastName"));
                    client.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
                    client.setPhone(resultSet.getString("phone"));
                    client.setAddress(resultSet.getString("address"));
                    client.setEmployee(new EmployeeDaoImpl().findByID(resultSet.getInt("employeematricule")).get());

                    return Optional.of(client);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM clients";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllSQL)) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setCode(resultSet.getInt("code"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setLastName(resultSet.getString("lastName"));
                client.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
                client.setPhone(resultSet.getString("phone"));
                client.setAddress(resultSet.getString("address"));

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Optional<Client> update(Integer id, Client client) {
        String updateSQL = "UPDATE clients " +
                "SET firstName = ?, lastName = ?, birthDate = ?, phone = ?, address = ? " +
                "WHERE code = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(client.getBirthDate()));
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.setString(5, client.getAddress());
            preparedStatement.setInt(6, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new Exception("Updating client failed, no rows affected.");
            }

            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        String deleteSQL = "DELETE FROM clients WHERE code = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        boolean deleted = false;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM clients");
            int rows = ps.executeUpdate();

            if (rows > 0) {
                deleted = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
