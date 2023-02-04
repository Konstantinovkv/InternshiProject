package internshipProject.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
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
    private static final String BR = "<br>";

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
        sendEmail(user, complaint);
    }

    @Override
    public List<Complaint> findComplaintsByUser(User user) {
        return complaintRepository.findComplaintsByUser(user);
    }

    public void deleteComplaint(long id) {
        complaintRepository.deleteById(id);
    }

    private void sendEmail(User user, ComplaintResponse complaint) throws MessagingException {
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
        msg.setFrom(new InternetAddress("complaints@gmail.com", true));
        StringBuilder message = new StringBuilder();
        message.append("Hello ").append(user.getUsername()).append(",").append(BR+BR)
                .append("Regarding your complaint '").append(complaint.getTopic())
                .append("' we are sorry that ").append(communicateWithAi(complaint))
                .append(".").append(BR+BR).append("Best Wishes,").append(BR)
                .append("Complaints Team");

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        msg.setSubject("Re:" + complaint.getTopic());
        msg.setContent(message.toString(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("", "text/html");
        Transport.send(msg);
    }

    private String communicateWithAi(ComplaintResponse complaint){
        String token = "";
        OpenAiService service = new OpenAiService(token);

        StringBuilder sb = new StringBuilder();

        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("davinci")
                .prompt("Regarding your complaint '" + complaint.getTopic() + "' we are sorry that ")
                .temperature(1.0)
                .user("testing")
                .build();

        service.createCompletion(completionRequest).getChoices().forEach(
                sb::append
        );
        String result = sb.toString();
        result = result.replace("CompletionChoice(text=", "");
        return result.replace(", index=0, logprobs=null, finish_reason=length)", "");
    }

}
