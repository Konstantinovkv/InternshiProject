package internshipProject.service;

import internshipProject.dao.Entity.User;
import internshipProject.dao.repository.AddressRepository;
import internshipProject.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private HttpSession session;

    public void registerUser(User user) {
       userRepository.save(user);
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            return false;
        }
        if (user.checkPassword(password)) {
            session.setAttribute(email, user);
            return true;
        }
        return false;
    }

}
