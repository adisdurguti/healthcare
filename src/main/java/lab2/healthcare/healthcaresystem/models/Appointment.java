package lab2.healthcare.healthcaresystem.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment implements Comparable<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppointment")
    private Long idAppointment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String time;
    private String description;
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(Long idAppointment, Date date, String time, String description, Patient patient, Doctor doctor) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.time = time;
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
    }

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
