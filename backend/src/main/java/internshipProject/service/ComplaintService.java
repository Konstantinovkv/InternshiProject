package internshipProject.service;

import internshipProject.dao.Entity.User;
import internshipProject.dto.ComplaintResponse;

public interface ComplaintService {

    void registerComplaint(ComplaintResponse complaint, User user);

    void deleteComplaint(long id);

}
