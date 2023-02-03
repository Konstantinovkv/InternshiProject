package internshipProject.service;

import internshipProject.dao.Entity.Complaint;
import internshipProject.dao.Entity.User;
import internshipProject.dto.ComplaintResponse;

import java.util.List;

public interface ComplaintService {

    void registerComplaint(ComplaintResponse complaint, User user);

    List<Complaint> findComplaintsByUser(User user);

    void deleteComplaint(long id);

}
