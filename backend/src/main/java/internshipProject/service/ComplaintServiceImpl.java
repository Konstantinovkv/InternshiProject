package internshipProject.service;

import internshipProject.dao.Entity.Complaint;
import internshipProject.dao.Entity.User;
import internshipProject.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import internshipProject.dao.repository.ComplaintRepository;
import internshipProject.dto.ComplaintResponse;

@Service
public class ComplaintServiceImpl implements ComplaintService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComplaintRepository repository;

    public Complaint getComplaintById(long complaintId) {
        final Complaint complaint = repository.findById(complaintId);
        if (complaint == null) {
            return null;
        }
        else return complaint;
    }

    public void registerComplaint(ComplaintResponse complaint, User user) {
        repository.save(new Complaint(new java.sql.Date(System.currentTimeMillis()), complaint.getText(), complaint.getTopic(), user));
    }

    public void deleteComplaint(long id) {
        repository.deleteById(id);
    }

}
