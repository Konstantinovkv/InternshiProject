package internshipProject.controllers;

import internshipProject.exceptions.NotLoggedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class BaseController {

    protected static final String LOGGED = "logged";

    @Autowired
    private HttpSession session;

    protected void validateLogged(HttpSession session) throws NotLoggedException {
        if (session.getAttribute(LOGGED) == null) {
            throw new NotLoggedException("Not Logged");
        }
    }

}
