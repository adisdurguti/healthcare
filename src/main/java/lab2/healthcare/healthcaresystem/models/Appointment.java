package lab2.healthcare.healthcaresystem.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment implements Comparable<Appointment>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppointment")
    private Long idAppointment;

    private Date date;

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

    public Appointment(Long idAppointment, Date date, String description, Patient patient, Doctor doctor) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.description = description;
        this.patient = patient;
        this.doctor = doctor;
    }



    @Override
    public int compareTo(Appointment o) {
        return this.getIdAppointment().compareTo(o.getIdAppointment());
    }
}
