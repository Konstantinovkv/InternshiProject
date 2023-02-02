package internshipProject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import internshipProject.dao.Entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Complaint findById(long complaintId);

    Complaint save(Complaint complaint);

    @Transactional
    void deleteById(long id);

}
