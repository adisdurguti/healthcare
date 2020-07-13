package lab2.healthcare.healthcaresystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diagnose")
@Getter
@Setter
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Date dateCreated;
    private String diagnosePatient;
    private String treatment;
    @OneToOne(optional = false)
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment")
    private Appointment appointment;
    @ManyToOne
    @JoinColumn(name = "idpatient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor doctor;

    public Diagnose() {
    }

    public Diagnose(Long id, Date dateCreated, String diagnosePatient, String treatment, Appointment appointment, Patient patient, Doctor doctor) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.diagnosePatient = diagnosePatient;
        this.treatment = treatment;
        this.appointment = appointment;
        this.patient = patient;
        this.doctor = doctor;
    }
}