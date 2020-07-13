package lab2.healthcare.healthcaresystem.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment implements Comparable<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppointment")
    private Long idAppointment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotBlank
    private String time;
    @NotBlank
    @Size(min = 5, max = 150)
    private String description;
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor doctor;

    @Override
    public int compareTo(Appointment o) {
        return this.getIdAppointment().compareTo(o.getIdAppointment());
    }

    public Date getDateOnly() throws ParseException {
        String date2 = this.date.toString();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        return date1;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
