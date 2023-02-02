package internshipProject.dto;

import internshipProject.dao.Entity.Address;
import lombok.Data;

@Data
public class UserResponse {

    private long userId;
    private String username;
    private String email;
    private String phone;
    private Address address;

}
