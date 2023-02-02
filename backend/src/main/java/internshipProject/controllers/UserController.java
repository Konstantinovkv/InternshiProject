package internshipProject.controllers;

import internshipProject.dto.LoginData;
import internshipProject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import internshipProject.dao.Entity.User;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl usi;

    @Autowired
    private HttpSession session;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        usi.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginData loginData) {
        if(!usi.login(loginData.getEmail(), loginData.getPassword())) {
            throw new UnauthorizedException();
        }
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static class UnauthorizedException extends RuntimeException {
    }

}
