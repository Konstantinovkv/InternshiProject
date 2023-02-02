package internshipProject.dto;

import java.util.Date;

import internshipProject.dao.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplaintResponse {

    private long complaintId;
    private Date date;
    private String text;
    private User user;
    private String topic;

}
