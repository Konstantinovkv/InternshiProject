package internshipProject.dao.Entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "complaint")
@Data
@NoArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complaintId;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull
    @Column(name = "topic", nullable = false)
    private String topic;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Complaint(@NotNull Date date, @NotNull String text,
                     @NotNull String topic, @NotNull User user) {
        this.date = date;
        this.text = text;
        this.topic = topic;
        this.user = user;
    }

}
