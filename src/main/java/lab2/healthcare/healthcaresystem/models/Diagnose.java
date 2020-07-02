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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDiagnosePatient() {
        return diagnosePatient;
    }

    public void setDiagnosePatient(String diagnosePatient) {
        this.diagnosePatient = diagnosePatient;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}