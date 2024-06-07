package cz.engeto.genesisResources.service;

import cz.engeto.genesisResources.model.User;
import cz.engeto.genesisResources.settings.Settings;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private static final String connectionString = "jdbc:mysql://localhost:3306/genesisresources";

    Connection connection = DriverManager.getConnection(connectionString,"root","Smilda290402");

    Statement statement = connection.createStatement();

    public UserService() throws SQLException {
    }

    public void createUser(User user) throws SQLException{
        String personId = generateUniquePersonID();
        String uuid = UUID.randomUUID().toString();
      statement.execute("INSERT INTO users (Name, Surname, PersonID, Uuid) VALUES('"+user.getName()+"','"
              +user.getSurname()+"','"
              +personId+"','"
              +uuid+"')");
    }

    private List<String> loadAvailablePersonIDs() {
        List<String> availablePersonIDs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Settings.getPersonid()))) {
            String line;
            while ((line = br.readLine()) != null) {
                availablePersonIDs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availablePersonIDs;
    }

    private List<String> getAllPersonIDs() throws SQLException {
        ResultSet resultSet;
        List<String> personIDs = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT PersonID FROM users");
        while (resultSet.next()) {
            personIDs.add(resultSet.getString("PersonID"));
        }
        return personIDs;
    }

    private String generateUniquePersonID() throws SQLException {
        List<String> existingPersonIDs = getAllPersonIDs();
        List<String> availablePersonIDs = loadAvailablePersonIDs();
        for (String personID : existingPersonIDs) {
            availablePersonIDs.remove(personID);
        }
        if (availablePersonIDs.isEmpty()) {
            throw new SQLException("No single person ID.");
        }
        return availablePersonIDs.get(0);
    }

    public User getUserById(int id) throws SQLException{
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT ID, Name, Surname FROM users WHERE ID = "+id);
        while (resultSet.next()){
            return new User(
                    resultSet.getLong("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Surname")
            );
        }
        return null;
    }

    public User getUserByIdDetailed(int id) throws SQLException{
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * FROM users WHERE ID = "+id);
        while (resultSet.next()){
            return new User(
                    resultSet.getLong("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Surname"),
                    resultSet.getString("PersonID"),
                    resultSet.getString("Uuid")
            );
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException{
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            User user = new User(
                    resultSet.getLong("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Surname")
            );
            userList.add(user);
        }
        return userList;
    }

    public List<User> getAllUsersDetailed() throws SQLException{
        ResultSet resultSet;
        List<User> userList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()){
            User user = new User(
                    resultSet.getLong("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Surname"),
                    resultSet.getString("PersonID"),
                    resultSet.getString("Uuid")
            );
            userList.add(user);
        }
        return userList;
    }

    public void updateUser(User user) throws SQLException {
        statement.execute("UPDATE users SET Name = '"
                + user.getName() + "', Surname = '"
                + user.getSurname() + "' WHERE ID = "
                + user.getId());
    }

    public void deleteUserById(int id) throws SQLException{
        statement.executeUpdate("DELETE FROM users WHERE ID = "+id);
    }

}