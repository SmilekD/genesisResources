package cz.engeto.genesisresources.service;

import cz.engeto.genesisresources.model.User;
import cz.engeto.genesisresources.settings.Settings;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//////////////////////////////// DISCORD JMÉNO: David S //////////////////////////////////////////////

@Service
public class UserService {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/genesisresources";

    Connection connection = DriverManager.getConnection(CONNECTION_STRING,"root","Smilda290402");

    Statement statement = connection.createStatement();

    public UserService() throws SQLException {
    }

    public boolean createUser(User user) {
        String uuid = UUID.randomUUID().toString();

        try {
            if (!isPersonIdValidAndAvailable(user.getPersonID())) {
                return false;
            }

            int num = statement.executeUpdate("INSERT INTO users (Name, Surname, PersonID, Uuid) VALUES('"
                    + user.getName() + "','"
                    + user.getSurname() + "','"
                    + user.getPersonID() + "','"
                    + uuid + "')");

            return num > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isPersonIdValidAndAvailable(String personID) {
        List<String> availablePersonIds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Settings.getPersonid()))) {
            String line;
            while ((line = br.readLine()) != null) {
                availablePersonIds.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!availablePersonIds.contains(personID)) {
            return false;
        }

        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM users WHERE PersonID = '" + personID + "'");
            if (resultSet.next() && resultSet.getInt("count") > 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
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

    public boolean updateUser(User user) throws SQLException {
        int num = statement.executeUpdate("UPDATE users SET Name = '"
                + user.getName() + "', Surname = '"
                + user.getSurname() + "' WHERE ID = "
                + user.getId());
        return num >0;
    }

    public boolean deleteUserById(int id) throws SQLException{
        int num =statement.executeUpdate("DELETE FROM users WHERE ID = "+id);
        return num>0;
    }

}