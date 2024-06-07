package cz.engeto.genesisResources.controller;

import cz.engeto.genesisResources.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cz.engeto.genesisResources.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    public void createUser(@RequestBody User user) throws SQLException{
        userService.createUser(user);
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id")int id)throws SQLException {
        return userService.getUserById(id);
    }

    @GetMapping("user/{id}/details")
    public User getUserByIdDetailed(@PathVariable("id")int id)throws  SQLException{
        return userService.getUserByIdDetailed(id);
    }

    @GetMapping("users")
    public List<User> getAllUsers() throws SQLException{
        return userService.getAllUsers();
    }

    @GetMapping("users/details")
    public List<User> getAllUsersDetailed() throws SQLException{
        return userService.getAllUsersDetailed();
    }

    @PutMapping("user")
    public void updateUser(@RequestBody User user) throws SQLException{
        userService.updateUser(user);
    }

    @DeleteMapping("user/{id}")
    public void deleteUserById(@PathVariable("id")int id) throws SQLException{
        userService.deleteUserById(id);
    }


}