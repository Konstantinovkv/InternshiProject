package internshipProject.service;

import internshipProject.dao.Entity.Complaint;
import internshipProject.dao.Entity.User;
import internshipProject.dao.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import internshipProject.dao.repository.ComplaintRepository;
import internshipProject.dto.ComplaintResponse;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class ComplaintServiceImpl implements ComplaintService {


    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}") private String username;
    @Value("${spring.mail.password}") private String password;

    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint getComplaintById(long complaintId) {
        final Complaint complaint = complaintRepository.findById(complaintId);
        if (complaint == null) {
            return null;
        } else return complaint;
    }

    @SneakyThrows
    public void registerComplaint(ComplaintResponse complaint, User user) {
        complaintRepository.save(new Complaint(new java.sql.Date(System.currentTimeMillis()), complaint.getText(), complaint.getTopic(), user));
        sendEmail(user);
    }

    @Override
    public List<Complaint> findComplaintsByUser(User user) {
        return complaintRepository.findComplaintsByUser(user);
    }

    public void deleteComplaint(long id) {
        complaintRepository.deleteById(id);
    }

    private void sendEmail(User user) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("complaints@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        msg.setSubject("Sorry to hear that");
        msg.setContent("We are sorry to hear about your problem " + user.getUsername() + "! " +
                "\nDrink some water, maybe it will help", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("", "text/html");
        Transport.send(msg);
    }

}
