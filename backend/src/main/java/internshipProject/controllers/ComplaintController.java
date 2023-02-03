package internshipProject.controllers;

import internshipProject.dao.Entity.Complaint;
import internshipProject.dao.Entity.User;
import internshipProject.dto.ComplaintResponse;
import internshipProject.service.ComplaintServiceImpl;
import internshipProject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping(value = "/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintServiceImpl csi;

    @Autowired
    private UserServiceImpl usi;

    @PostMapping("/register")
    public ResponseEntity registerComplaint(@RequestBody ComplaintResponse complaint) {
        User user = usi.getUserByEmail(complaint.getEmail());
        csi.registerComplaint(complaint, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Complaint>> getComplaintsByEmail(@PathVariable String email) {
        User user = usi.getUserByEmail(email);
        List<Complaint> complaints = csi.findComplaintsByUser(user);
        if (complaints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComplaint(@PathVariable(value = "id") long id) {
        csi.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
