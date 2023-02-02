package internshipProject.service;

import internshipProject.dto.ComplaintResponse;

public interface ComplaintService {

    void registerComplaint(ComplaintResponse complaint);

    void deleteComplaint(long id);

}
