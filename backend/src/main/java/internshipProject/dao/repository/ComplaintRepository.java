package internshipProject.dao.repository;

import internshipProject.dao.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import internshipProject.dao.Entity.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Complaint findById(long complaintId);

    Complaint save(Complaint complaint);

    List<Complaint> findComplaintsByUser(User user);

    @Transactional
    void deleteById(long id);

}
