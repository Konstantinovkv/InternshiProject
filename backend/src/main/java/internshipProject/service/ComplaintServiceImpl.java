package internshipProject.service;

import internshipProject.dao.Entity.Complaint;
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

    public ComplaintResponse getComplaintById(long complaintId) {
        final Complaint complaint = repository.findById(complaintId);
        if (complaint == null) {
            return null;
        }
        return new ComplaintResponse(complaint.getComplaintId(), complaint.getDate(),
                complaint.getText(), complaint.getUser(),
                complaint.getTopic());
    }

    public void registerComplaint(ComplaintResponse complaint) {
        repository.save(new Complaint(complaint.getDate(), complaint.getText(), complaint.getTopic(), complaint.getUser()));
    }

    public void deleteComplaint(long id) {
        repository.deleteById(id);
    }

}
