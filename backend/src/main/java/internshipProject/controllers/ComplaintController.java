package internshipProject.controllers;

import internshipProject.dto.ComplaintResponse;
import internshipProject.service.ComplaintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintServiceImpl csi;

    @PostMapping("/register")
    public ResponseEntity registerComplaint(@RequestBody ComplaintResponse complaint) {
        csi.registerComplaint(complaint);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComplaint(@PathVariable(value = "id") long id) {
        csi.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
