package lab2.healthcare.healthcaresystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 5, max = 200)
    private String diagnosePatient;
    @NotBlank
    @Size(min = 5, max = 200)
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
}