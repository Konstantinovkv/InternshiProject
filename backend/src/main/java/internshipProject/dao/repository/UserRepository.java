package internshipProject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import internshipProject.dao.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    User findByEmail(String email);

}


