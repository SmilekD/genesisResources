package cz.engeto.genesisresources.controller;

import cz.engeto.genesisresources.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.engeto.genesisresources.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    public ResponseEntity<String> createUser(@RequestBody User user) throws SQLException{
        boolean isCreated = userService.createUser(user);
        if (isCreated){
            return ResponseEntity.ok("Uživatel byl úspěšně vytvořen");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vaše PersonID neexistuje " +
                    "nebo je již použité. Vložte prosím jiné.");
        }
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
    public ResponseEntity<String> updateUser(@RequestBody User user) throws SQLException{
        boolean isUpdated = userService.updateUser(user);
        if (isUpdated){
            return ResponseEntity.ok("Uživatel byl úspěšně aktualizován");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uživatel nebyl nalezen");
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id")int id) throws SQLException{
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted){
            return ResponseEntity.ok("Uživatel byl úspěšně vymazán");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Uživatel nebyl nalezen");
        }
    }


}