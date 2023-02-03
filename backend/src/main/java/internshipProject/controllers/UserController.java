package internshipProject.controllers;

import internshipProject.dto.LoginData;
import internshipProject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import internshipProject.dao.Entity.User;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping(value = "/user")
public class UserController{

    @Autowired
    private UserServiceImpl usi;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        usi.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody LoginData loginData) {
        return !usi.login(loginData.getEmail(), loginData.getPassword())
                ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED)
                : new ResponseEntity<>(HttpStatus.OK);
    }

}
